package com.alura.forohub.forohubapi.domain.topics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleIgnoreCaseAndMessageIgnoreCase(String title, String message);

    Page<Topic> findAllByOrderByCreatedAtAsc(Pageable pageable);
    Page<Topic> findByCourseIgnoreCaseOrderByCreatedAtAsc(String course, Pageable pageable);
    Page<Topic> findByCreatedAtBetweenOrderByCreatedAtAsc(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Topic> findByCourseIgnoreCaseAndCreatedAtBetweenOrderByCreatedAtAsc(String course, LocalDateTime start, LocalDateTime end, Pageable pageable);

    boolean existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdNot(String title, String message, Long id);

}