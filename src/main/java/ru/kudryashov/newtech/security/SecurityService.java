package ru.kudryashov.newtech.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.kudryashov.newtech.consts.ProjectConsts;
import ru.kudryashov.newtech.entities.User;
import ru.kudryashov.newtech.exceptions.AuthException;
import ru.kudryashov.newtech.services.UserService;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expirationInSeconds;
    @Value("${jwt.issuer}")
    private String issuer;

    public Mono<TokenDetails> authenticate(String username, String password) {
        return userService.getUserByUsername(username)
                .flatMap(user -> {
                    if (!user.isEnabled()) {
                        return Mono.error(new AuthException("the account is disabled", "auth.account.disabled"));
                    }

                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.error(new AuthException("invalid password", "auth.password.invalid"));
                    }

                    return Mono.just(generateToken(user).toBuilder()
                            .userId(user.getId())
                            .build());
                })
                .switchIfEmpty(Mono.error(new AuthException("invalid username", "auth.username.invalid")));
    }

    private TokenDetails generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ProjectConsts.ROLE, user.getRole());
        claims.put(ProjectConsts.USERNAME, user.getUsername());
        return generateToken(claims, String.valueOf(user.getId()));
    }

    private TokenDetails generateToken(Map<String, Object> claims, String subject) {
        long expirationTimeInMillis = expirationInSeconds * 1000L;
        Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

        return generateToken(expirationDate, claims, subject);
    }

    private TokenDetails generateToken(Date expirationDate, Map<String, Object> claims, String subject) {
        Date issuedAt = new Date();
        String token = Jwts.builder()
                .addClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();

        return TokenDetails.builder()
                .token(token)
                .expiresAt(expirationDate)
                .issuedAt(issuedAt)
                .build();
    }

    /*
     * токен содержит: заголовок, payload и подпись
     */
}
