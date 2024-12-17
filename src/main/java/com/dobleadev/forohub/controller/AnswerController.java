package com.dobleadev.forohub.controller;

import com.dobleadev.forohub.domain.answer.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerPosting posting;

    @GetMapping
    public ResponseEntity<Page<DataListAnswer>> getAll(Pageable pagination) {
        return ResponseEntity.ok(answerRepository.findByActiveTrue(pagination).map(DataListAnswer::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataListAnswer> getById(@PathVariable Long id) {
        System.out.println(id);
        var answer = answerRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataListAnswer(answer));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataListAnswer> create(@RequestBody @Valid DataRegisterAnswer answer, UriComponentsBuilder uriComponentsBuilder) {
        var newAnswer = answerRepository.save(posting.Post(answer));
        System.out.println("Answer creado exitosamente");
        URI url = uriComponentsBuilder.path("/answers/{id}").buildAndExpand(newAnswer.getId()).toUri();
        return ResponseEntity.created(url).body(new DataListAnswer(newAnswer));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataListAnswer> update(@RequestBody @Valid DataUpdateAnswer answer) {
        var answerToUpdate = answerRepository.getReferenceById(answer.id());
        answerToUpdate.updateData(answer);
        System.out.println("Answer actualizado exitosamente");
        return ResponseEntity.ok(new DataListAnswer(answerToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var answerToDelete = answerRepository.getReferenceById(id);
        answerToDelete.deactivate();
        System.out.println("Answer eliminado exitosamente");
        return ResponseEntity.noContent().build();
    }
}
