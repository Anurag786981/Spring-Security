package com.example.SpringSecurity.SpringSecurity.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final long EXPIRATION_TIME=10000*60*60;// 1hour
    private final String SECRET="talk-helmet-woman-L3u3SATjjjdYyI4bg3pDvJKPUToVh8mxqhU2WXyHUgs=";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
         return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUserName(String token){
      Claims body = Jwts.parser()
              .verifyWith(key)
              .build()
              .parseSignedClaims(token)
              .getPayload();

      return body.getSubject();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
        //check if username is same as username in userdetails
        
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        //validating token expiry
        Claims body = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return body.getExpiration().before(new Date());

    }
}
