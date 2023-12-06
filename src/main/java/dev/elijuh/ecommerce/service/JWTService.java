package dev.elijuh.ecommerce.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

/**
 * @author elijuh
 */

@Service
public class JWTService {

    private final JwtParser parser;
    private final JwtBuilder builder;

    public JWTService(@Value("${jwt.signingKey}") String signingKey, @Value("${jwt.issuer}") String issuer) {
        Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(signingKey));
        this.parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .build();

        this.builder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuer(issuer);
    }

    public Claims getClaims(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return builder.setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 2592000000L))
                .setSubject(userDetails.getUsername())
                .compact();
    }

    public boolean isTokenValid(Claims claims, UserDetails userDetails) {
        String username = claims.getSubject();
        return Objects.equals(username, userDetails.getUsername())
                && claims.getExpiration().after(new Date());
    }
}
