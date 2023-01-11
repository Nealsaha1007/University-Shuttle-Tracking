package com.OOD.Project1.repository;

import com.OOD.Project1.model.Shuttle;
import com.OOD.Project1.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
	
	List<Student> findStudentsByShuttle(Shuttle shuttleID);

}
