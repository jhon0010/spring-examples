package main.infrastructure.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.application.services.CustomUserDetailsService;
import main.application.services.JwtService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Filter in charge of JWT token validation, executed once per each http request.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ApplicationContext context;
    private final List<String> publicEndpoints = Arrays.asList("/users/login", "/users/register");

    public JwtFilter(JwtService jwtService, ApplicationContext context){
        this.jwtService = jwtService;
        this.context = context;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return publicEndpoints.contains(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = "";
        String username  = "";

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            token = authorizationHeader.substring(7);// remove Bearer
            username = jwtService.extractUsernameFromToken(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)){

                // This token is require for the username password authentication filter executed after this
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
