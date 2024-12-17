package com.dobleadev.forohub.controller;

import com.dobleadev.forohub.domain.course.*;
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

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<Page<DataListCourse>> getAll(Pageable pagination) {
        return ResponseEntity.ok(courseRepository.findByActiveTrue(pagination).map(DataListCourse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataListCourse> getById(@PathVariable Long id) {
        System.out.println(id);
        var course = courseRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataListCourse(course));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataListCourse> create(@RequestBody @Valid DataRegisterCourse course, UriComponentsBuilder uriComponentsBuilder) {
        var newCourse = courseRepository.save(new Course(course));
        System.out.println("Course creado exitosamente");
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(newCourse.getId()).toUri();
        return ResponseEntity.created(url).body(new DataListCourse(newCourse));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataListCourse> update(@RequestBody @Valid DataUpdateCourse course) {
        var courseToUpdate = courseRepository.getReferenceById(course.id());
        courseToUpdate.updateData(course);
        System.out.println("Course actualizado exitosamente");
        return ResponseEntity.ok(new DataListCourse(courseToUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var courseToDelete = courseRepository.getReferenceById(id);
        courseToDelete.deactivate();
        System.out.println("Course eliminado exitosamente");
        return ResponseEntity.noContent().build();
    }
}
