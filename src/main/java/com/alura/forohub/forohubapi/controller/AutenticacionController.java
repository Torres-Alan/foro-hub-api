package com.alura.forohub.forohubapi.controller;

import com.alura.forohub.forohubapi.domain.users.DatosAutenticacionUsuario;
import com.alura.forohub.forohubapi.domain.users.User;
import com.alura.forohub.forohubapi.infra.security.DatosJWTToken;
import com.alura.forohub.forohubapi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwt = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwt));
    }
}
