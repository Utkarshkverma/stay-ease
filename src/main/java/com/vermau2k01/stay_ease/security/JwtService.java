package com.vermau2k01.stay_ease.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private long EXPIRATION_TIME;


    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "stay-ease.com");

        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date
                        .from(Instant.now()
                                .plusSeconds(EXPIRATION_TIME)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey()
    {
        byte[] decode = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }


}
