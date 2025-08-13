package com.alura.forohub.forohubapi.domain.topics;

import java.time.LocalDateTime;

public record DatosListadoTopic(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        String status,
        String author,
        String course
) {
    public DatosListadoTopic(Topic t) {
        this(t.getId(), t.getTitle(), t.getMessage(),
                t.getCreatedAt(), t.getStatus().name(),
                t.getAuthor(), t.getCourse());
    }
}
