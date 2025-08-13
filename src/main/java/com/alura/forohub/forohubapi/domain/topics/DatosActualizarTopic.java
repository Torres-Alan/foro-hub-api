package com.alura.forohub.forohubapi.domain.topics;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopic(
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String author,
        @NotBlank String course
) {}
