package com.alura.forohub.forohubapi.controller;

import com.alura.forohub.forohubapi.domain.topics.*;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopic> registrarTopic(
            @RequestBody @Valid DatosRegistroTopic datosRegistroTopic,
            UriComponentsBuilder uriComponentsBuilder) {


        if (topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCase(
                datosRegistroTopic.title(),
                datosRegistroTopic.message())) {
            return ResponseEntity.badRequest().build();
        }


        Topic topic = new Topic();
        topic.setTitle(datosRegistroTopic.title());
        topic.setMessage(datosRegistroTopic.message());
        topic.setAuthor(datosRegistroTopic.author());
        topic.setCourse(datosRegistroTopic.course());


        Topic savedTopic = topicRepository.save(topic);


        DatosRespuestaTopic datosRespuestaTopic = new DatosRespuestaTopic(
                savedTopic.getId(),
                savedTopic.getTitle(),
                savedTopic.getMessage(),
                savedTopic.getCreatedAt(),
                savedTopic.getStatus().toString(),
                savedTopic.getAuthor(),
                savedTopic.getCourse()
        );


        URI url = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(savedTopic.getId())
                .toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopic>> listar(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC)
            Pageable pageable) {

        var page = topicRepository.findAll(pageable).map(DatosListadoTopic::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopic> obtenerPorId(@PathVariable Long id) {
        return topicRepository.findById(id)
                .map(t -> new DatosRespuestaTopic(
                        t.getId(),
                        t.getTitle(),
                        t.getMessage(),
                        t.getCreatedAt(),
                        t.getStatus().name(),
                        t.getAuthor(),
                        t.getCourse()
                ))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopic> actualizarTopic(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopic datos) {

        var opt = topicRepository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        if (topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdNot(
                datos.title(), datos.message(), id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        var topic = opt.get();
        topic.setTitle(datos.title());
        topic.setMessage(datos.message());
        topic.setAuthor(datos.author());
        topic.setCourse(datos.course());


        var resp = new DatosRespuestaTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreatedAt(),
                topic.getStatus().name(),
                topic.getAuthor(),
                topic.getCourse()
        );

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopic(@PathVariable Long id) {
        if (!topicRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
