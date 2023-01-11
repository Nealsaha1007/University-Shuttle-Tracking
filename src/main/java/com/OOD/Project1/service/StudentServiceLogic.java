package com.OOD.Project1.service;

import com.OOD.Project1.dtos.DtoMapperStudent;
import com.OOD.Project1.dtos.StudentDto;
import com.OOD.Project1.model.Shuttle;
import com.OOD.Project1.model.Student;
import com.OOD.Project1.repository.ShuttleRepo;
import com.OOD.Project1.repository.StudentRepo;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceLogic implements StudentService {

    private final StudentRepo studentRepo;
	
	private final  ShuttleRepo shuttleRepo;

	public StudentServiceLogic(StudentRepo studentRepo, ShuttleRepo shuttleRepo){
		this.studentRepo = studentRepo;
		this.shuttleRepo = shuttleRepo;
	}


    @Override
    public boolean isValid(Long suid) {
        return studentRepo.findById(suid).isPresent();
    }

    @Override
    public Student save(StudentDto student) {
    	// checking if the user with particular id exists here already
    	
    	Optional<Student> checkValidStudent=studentRepo.findById(student.getId());
    	if(checkValidStudent.isPresent()) {
    		
    		return null;
    		
    	}
    	
    	
    DtoMapperStudent studentMapper =new DtoMapperStudent();
    try {
    	
		return studentRepo.save(studentMapper.convertToEntity(student));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return null;
    }

    @Override
    public Student getStudent(Long suid) {
    	var student=studentRepo.findById(suid);
    	if (student.isPresent()) {
    		return student.get();
    	}
        return null;
    }

	@Override
	public Student updateStudent(Long suid,Shuttle shuttle,String dropAddress){//},String pickUpAddress) {
		Student student=studentRepo.findById(suid).get();
		student.setShuttle(shuttle);
		student.setDropAddress(dropAddress);
		//student.setHomeAddress(pickUpAddress);
		
		return studentRepo.save(student);
	}

	@Override
	public List<Student> getStudentByShuttle(Shuttle shuttleID) {
		return studentRepo.findStudentsByShuttle(shuttleID);
	}
}
