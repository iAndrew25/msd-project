package org.app.service.ejb.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ExamService;
import org.app.service.ejb.ExamServiceEJB;
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
public class TestDataServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestDataServiceEJBArq.class.getName());
	
	// Arquilian infrastructure
	@EJB
	private static ExamService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "MSD-Project.war")
	                .addPackage(Exam.class.getPackage())
	                .addPackage(ExamService.class.getPackage())
	                .addPackage(ExamServiceEJB.class.getPackage())
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
	public void test1_getExams() {
		Collection<Exam> tests = service.getExams();
		assertTrue("Manage to read tests", tests.size() > 0);
		logger.info("DEBUG: EJB Response ..." + tests);
	}

	@Test
	public void test2_deleteExams() {
		Collection<Exam> exams = service.getExams();
		
		for(Exam e: exams) {
		//	service.removeExam(e);
		}
		Collection<Exam> examsAfterDelete = service.getExams();
		assertTrue("Manage to read tests", examsAfterDelete.size() > 0);
		logger.info("DEBUG: EJB Response ..." + examsAfterDelete);
	}	
	
	@Test
	public void test3_addExams() {
		int examsToAdd = 3;

		for(int i = 1; i <= examsToAdd; i++) {
		//	service.addExam(new Exam(i, "Exam " + 1, "Math", "1st of Nov"));
			//Test t = new Test(0, "Test");
			//service.AddTest(new org.app.service.entities.Test(i, "Test_" + 1));
		}
		Collection<Exam> exams = service.getExams();
		assertTrue("Manage to add tests", exams.size() == 3);
	}	

}
