package org.app.service.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Id;


public class Exam implements Serializable {

		public Exam(int id, String name, String subject, String date) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.exam_date = date;
	}
	@Id @GeneratedValue
	private int id;
	private String name;
	private String subject;
	private String exam_date;	
}
