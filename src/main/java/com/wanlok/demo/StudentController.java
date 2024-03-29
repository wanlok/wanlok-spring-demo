package com.wanlok.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/student")
    public ResponseEntity<Student> post(@RequestBody Student student) {
        student = repository.save(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/student")
    public ResponseEntity<Student> delete(@RequestParam(value="student_id") Long student_id) {
        Optional<Student> student = repository.findById(student_id);
        if (student.isPresent()) {
            repository.delete(student.get());
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
