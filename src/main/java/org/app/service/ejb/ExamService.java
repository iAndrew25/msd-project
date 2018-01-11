package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Exam;
import org.app.service.entities.Test;


@Remote
public interface ExamService {

	//CREATE
	Exam addExam(Exam exam);
	
	//DELETE
	String removeExam(Exam exam);
	
	//READ
	Exam getExamById(int id);
	Collection<Exam> getExams();

	String sayRest();
}