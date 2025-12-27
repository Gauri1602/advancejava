package com.example.student.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1. Register Student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student saved = service.saveStudent(student);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 2. View All Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // 3. Search Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = service.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 4. Update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        Student updated = service.updateStudent(id, student);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // 5. Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        if (service.deleteStudent(id)) {
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student not found");
    }
}
