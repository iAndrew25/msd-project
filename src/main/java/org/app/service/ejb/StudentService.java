package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Student;

@Remote
public interface StudentService {

	//CREATE
	//Student addStudent(Student student);
	
	//DELETE
	String removeStudent(String id);
	//String removeStudent(Student student);
	
	//READ
	Student getStudentById(String id);
	Collection<Student> getStudents();

	String sayRest();

	Student updateStudent(Student student);

	Student addStudent(Student student);
}