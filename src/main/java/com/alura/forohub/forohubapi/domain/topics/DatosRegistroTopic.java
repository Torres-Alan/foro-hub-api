package com.alura.forohub.forohubapi.domain.topics;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopic(
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String author,
        @NotBlank String course
) {
}
