package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Student updateStudent(int id, Student student) {
        Optional<Student> existing = repo.findById(id);
        if (existing.isPresent()) {
            Student s = existing.get();
            s.setName(student.getName());
            s.setCourse(student.getCourse());
            s.setEmail(student.getEmail());
            s.setMarks(student.getMarks());
            return repo.save(s);
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
