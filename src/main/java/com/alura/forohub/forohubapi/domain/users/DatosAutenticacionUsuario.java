package com.alura.forohub.forohubapi.domain.users;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank String login,
        @NotBlank String clave
) {}
