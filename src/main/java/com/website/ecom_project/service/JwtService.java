package com.website.ecom_project.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long expirationTime;


    public String generateToken(Authentication authentication) {
        
        Object principal = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String email = userDetails.getUsername();
        
        String token =  JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secretKey));

        return token;
    }

    public String extractEmail(String token){
       
        String email = JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
        .build()
        .verify(token)
        .getSubject();

        return email;
    }
}

