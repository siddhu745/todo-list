package com.siddhu.todo_list.security;

import com.siddhu.todo_list.exception.InvalidTokenException;
import com.siddhu.todo_list.user.UserAuthDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication, UserAuthDto userAuthDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",authentication.getAuthorities());
        return createToken(claims,userAuthDto.email());
    }

    private String createToken(Map<String, Object> claims, String email) {

       return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    public boolean validateToken(String token) {
        Date expirationDate = extractExpiration(token);
        return expirationDate.after(new Date());
    }

    public String extractSubject(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(
                secretKey.getBytes(),
                getSignKey().getAlgorithm()
        );

        try {
            return Jwts.parser()
                    .verifyWith(secretKeySpec)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }



    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
