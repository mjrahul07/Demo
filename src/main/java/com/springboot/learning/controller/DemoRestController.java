package com.springboot.learning.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.springboot.learning.ErrorResponse.ErrorResponse;
import com.springboot.learning.StudentNotFoundException.StudentNotFoundException;
import com.springboot.learning.entity.StudentPOJO;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class DemoRestController {
	
	
	private List<StudentPOJO> studentList=new ArrayList<>();
	
	@PostConstruct
	public void postData() {
		studentList.add(new StudentPOJO(1,"Rahul","N"));
		studentList.add(new StudentPOJO(2,"Jerry","B"));
		studentList.add(new StudentPOJO(3,"Tom","F"));
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World.!";
	}
	
	@GetMapping("/students")
	public List<StudentPOJO> allStudents(){
		return studentList;
		
	}
	
	@GetMapping("/students/{index}")
	public StudentPOJO studentWithId(@PathVariable int index){
		
		if(index>=studentList.size()||index<0) {
			throw new StudentNotFoundException(index + " is an invalid index");
		}
		return studentList.get(index) ;
		
	}
	
	

}
