package com.alura.forohub.forohubapi.domain.topics;

import java.time.LocalDateTime;

public record DatosRespuestaTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        String status,
        String author,
        String course
) {
}
