package org.app.service.ejb.exam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.TestService;
import org.app.service.ejb.TestServiceEJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.app.service.entities.*;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExamDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(ExamDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB
	private static TestService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-Project.war")
	                .addPackage(Test.class.getPackage())
	                .addPackage(TestService.class.getPackage())
	                .addPackage(TestServiceEJB.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	
	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test1_getTests() {
		Collection<org.app.service.entities.Test> tests = service.getTests();
		assertTrue("Manage to read tests", tests.size() > 0);
		logger.info("DEBUG: EJB Response ..." + tests);
	}

	@Test
	public void test2_deleteTests() {
		Collection<org.app.service.entities.Test> tests = service.getTests();
		
		for(org.app.service.entities.Test t: tests) {
			service.RemoveTest(t);
		}
		Collection<org.app.service.entities.Test> testsAfterDelete = service.getTests();
		assertTrue("Manage to read tests", tests.size() > 0);
		logger.info("DEBUG: EJB Response ..." + tests);
	}	
	
	@Test
	public void test3_addTests() {
		int testsToAdd = 3;
		List<Questions> questions =  new ArrayList<>();
		for(int i = 1; i <= testsToAdd; i++) {
			//Test t = new Test(0, "Test");
			//service.AddTest(new org.app.service.entities.Test(i, "Test_" + 1));
		}
		Collection<org.app.service.entities.Test> tests = service.getTests();
		assertTrue("Manage to add tests", tests.size() == 3);
	}	

}
