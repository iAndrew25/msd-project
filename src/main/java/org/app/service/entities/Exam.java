package org.app.service.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name="exam")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exam implements Serializable {
public Exam() {}
	public Exam(int id, String name, String subject, String date) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.exam_date = date;
	}
	@Id @GeneratedValue
	private Integer id;
	private String name;
	private String subject;
	private String exam_date;	
	
	@XmlElement
	@JsonProperty("id")
	public Integer getExamId() {
		return this.id;
	}

	public void setExamId(Integer id) {
		this.id = id;
	}
	@XmlElement
	@JsonProperty("name")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	@JsonProperty("subject")
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@XmlElement
	@JsonProperty("date")
	public String getDate() {
		return this.exam_date;
	}
	public void setDate(String exam_date) {
		this.exam_date = exam_date;
	}
}
