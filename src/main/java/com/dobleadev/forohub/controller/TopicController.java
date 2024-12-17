package com.dobleadev.forohub.controller;

import com.dobleadev.forohub.domain.topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicPosting topicPosting;

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> getAll(@PageableDefault(size = 2) Pageable pagination) {
        return ResponseEntity.ok(topicRepository.findByActiveTrue(pagination).map(DataListTopic::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailsTopic> getById(@PathVariable Long id) {
        System.out.println(id);
        var topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsTopic(topic));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataResultTopic> create(@RequestBody @Valid DataRegisterTopic topic, UriComponentsBuilder uriComponentsBuilder) {
        var newTopic = topicRepository.save(topicPosting.Post(topic));
        System.out.println("Topico creado exitosamente");
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(newTopic.getId()).toUri();
        return ResponseEntity.created(url).body(new DataResultTopic(newTopic));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResultTopic> update(@RequestBody @Valid DataUpdateTopic topic) {
        var topicToUpdate = topicRepository.getReferenceById(topic.id());
        topicToUpdate.updateData(topic);
        System.out.println("Topico actualizado exitosamente");
        return ResponseEntity.ok(new DataResultTopic(topicToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var topicToDelete = topicRepository.getReferenceById(id);
        topicToDelete.deactivate();
        System.out.println("Topico eliminado exitosamente");
        return ResponseEntity.noContent().build();
    }
}
