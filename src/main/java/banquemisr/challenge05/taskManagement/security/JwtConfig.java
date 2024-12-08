package banquemisr.challenge05.taskManagement.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtConfig {
    private String secretKey = "mostafa";

    public String getnerateToken(String usermame){
        return Jwts.builder()
                .setSubject(usermame)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    public String extractUsermame(String tolen){
        return String.valueOf(extractClaims(tolen));
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsermame(token)) && !isTokenExpired(token));
    }
}
