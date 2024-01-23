package com.wanlok.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentRepository repository;

    public StudentController() {

    }

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/student")
    public List<Student> list() {
        return repository.findAll();
    }

    @GetMapping("/student/{student_id}")
    public ResponseEntity<Student> view(@PathVariable(value="student_id") Long student_id) {
        Optional<Student> student = repository.findById(student_id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
