package com.OOD.Project1.service;

import java.util.List;

import com.OOD.Project1.dtos.StudentDto;
import com.OOD.Project1.model.Shuttle;
import com.OOD.Project1.model.Student;

public interface StudentService {
    boolean isValid(Long suid);

    Student save(StudentDto student);

    Student getStudent(Long suid);
    
    List<Student> getStudentByShuttle (Shuttle shuttle);
    


	Student updateStudent(Long suid, Shuttle shuttle, String dropAddress);//, String pickUpAddress);
    
}
