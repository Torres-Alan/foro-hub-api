package com.alura.forohub.forohubapi.infra.security;

import com.alura.forohub.forohubapi.domain.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "foro-hub-api";

    public String generarToken(User usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            var zone = ZoneId.of("America/Merida");
            var now = LocalDateTime.now(zone);
            var exp = now.plusHours(2);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getUsername())
                    .withIssuedAt(Date.from(now.atZone(zone).toInstant()))
                    .withExpiresAt(Date.from(exp.atZone(zone).toInstant()))
                    .sign(algoritmo);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido o expirado");
        }
    }
}
