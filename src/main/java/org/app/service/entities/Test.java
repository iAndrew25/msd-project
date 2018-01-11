package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Test implements Serializable {
	@Id @GeneratedValue
	private int TestId;
	private String TestName;
	
	@OneToMany(mappedBy="test", orphanRemoval = false)
	private List<Questions> questions =  new ArrayList<>();
	
}
