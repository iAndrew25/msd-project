package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;
import org.app.service.entities.Test;


@Remote
public interface TestService {

	//CREATE
	Test AddTest(Test testToAdd);
	
	//DELETE
	String RemoveTest(Test testToRemove);
	
	//READ
	Test GetTestById(int testID);
	Collection<Test> getTests();

	String sayRest();
	
	

}