package main.application.services;

import main.domain.models.Users;
import main.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsersService(UserRepository userRepository, AuthenticationManager authenticationManager,
                        JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public Users saveUser(Users user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public String login(Users user) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        return authentication.isAuthenticated() ? this.jwtService.generateToken(user.getUsername()) : "Login failed";
    }
}
