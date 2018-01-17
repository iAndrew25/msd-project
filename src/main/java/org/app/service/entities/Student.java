package org.app.service.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name="student")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {
public Student() {}

	@Id @GeneratedValue
	private int id; 
	private String name; 
	private int math; 
	private int prog; 
	private int db; 
	private int linux; 	

	public Student(int id, String name, int math, int prog, int db, int linux) {
		this.id = id;
		this.name = name;
		this.math = math;
		this.prog = prog;
		this.db = db;
		this.linux = linux;
	}
	
	@XmlElement
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	@JsonProperty("math")
	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	@XmlElement
	@JsonProperty("prog")
	public int getProg() {
		return prog;
	}

	public void setProg(int prog) {
		this.prog = prog;
	}
	
	@XmlElement
	@JsonProperty("db")
	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		this.db = db;
	}
	
	@XmlElement
	@JsonProperty("linux")
	public int getLinux() {
		return linux;
	}

	public void setLinux(int linux) {
		this.linux = linux;
	}
	
	@XmlElement
	@JsonProperty("name")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}