package com.checkers.gameapi.security;

import com.checkers.gameapi.model.UsrEntity;
import com.checkers.gameapi.repositories.UsrsRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long validInMillis;

    private final UsrsRepository usrsRepository;
//    private final SessionRepository sessionRepository;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(UsrEntity usr) throws IllegalArgumentException {
        if (nonNull(usr))
            return createToken(usr.getUsrName());
        else
            throw new IllegalArgumentException("Invalid usr. Usr is null.");
    }

    private String createToken(String userName) {
        Claims claims = Jwts.claims().setSubject(userName);

        Date now = new Date();
        Date validDate = new Date(now.getTime() + validInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

//    public String get

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("auth");
        if (bearerToken != null && bearerToken.startsWith("brr_"))
            return bearerToken.substring(3);
        return null;
    }

    public boolean validToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date()))
                return false;

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalStateException("JWT token is expired or invalid.");
        }
    }
}
