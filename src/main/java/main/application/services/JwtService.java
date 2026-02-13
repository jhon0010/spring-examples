package main.application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final SecretKey key;

    public JwtService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            this.key = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        Map<String,String> claims = new HashMap<>();

        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(key)
            .compact();
    }
    /*
        private SecretKey generateKey() {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
                keyGenerator.init(256);
                return keyGenerator.generateKey();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/

    public String extractUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isExpiredToken(token));
    }

    private boolean isExpiredToken(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    /**
     * Get claim for given a function of type T.
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Step-by-step breakdown:
     *
     * Jwts.parser() - Creates a JWT parser instance
     * .verifyWith(generateKey()) - Sets the signing key for verification (same key used to sign the token)
     * .build() - Builds the parser with the configured key
     * .parseSignedClaims(token) - Parses and validates the token signature and expiration
     * .getPayload() - Returns the claims payload as a Claims object
     * @param token token
     * @return Claims object
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
