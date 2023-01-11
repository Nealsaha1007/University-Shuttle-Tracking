package com.OOD.Project1.dtos;

import java.text.ParseException;

import org.modelmapper.ModelMapper;

import com.OOD.Project1.model.Student;



public class DtoMapperStudent {
	
	
	
	public Student convertToEntity(StudentDto postDto) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		Student post = modelMapper.map(postDto, Student.class);
	   return post;
	}

}
