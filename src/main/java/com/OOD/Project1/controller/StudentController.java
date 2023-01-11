package com.OOD.Project1.controller;

import com.OOD.Project1.dtos.StudentDto;
import com.OOD.Project1.model.Student;
import com.OOD.Project1.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentServiceInstance;

    public StudentController(StudentService studentServiceInstance) {
        this.studentServiceInstance = studentServiceInstance;
    }

    //add new student
    @PostMapping("/")
    public String saveStudent(@RequestBody StudentDto student) {
        return studentServiceInstance.save(student) != null ? "Saved" : "Duplicate Entry";
    }
    
    @GetMapping("/")
    public Student getStudentById(@RequestParam String studentId) {
        return studentServiceInstance.getStudent(Long.valueOf(studentId));
    }
}
