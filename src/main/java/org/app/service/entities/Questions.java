package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Questions implements Serializable {
	@Id @GeneratedValue
	private int QuestionId;
	private String Description;
	private String CorrectAnswer;
	
	@ManyToOne
	private Test test;
}
