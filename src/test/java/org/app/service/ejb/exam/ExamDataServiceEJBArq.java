package org.app.service.ejb.exam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.ExamService;
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
	private static ExamService service;
	
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
	public void exam1_getExams() {
		Collection<Exam> exams = service.getExams();
		assertTrue("Manage to read exams", exams.size() > 0);
		logger.info("DEBUG: EJB Response ..." + exams);
	}

	@Test
	public void test2_deleteExams() {
		Collection<Exam> exams = service.getExams();
		int countExams = exams.size();
		for(Exam e:exams) {
			//service.removeExam(e);
		}
		Collection<Exam> examsAfterDelete = service.getExams();
		assertTrue("Exam was deleted", examsAfterDelete.size() == countExams - 1);
	}	
	
	@Test
	public void test3_addTests() {
		int examsToAdd = 3;
		List<Questions> questions =  new ArrayList<>();
		Collection<Exam> exams = service.getExams();
		int countExams = exams.size();
		for(int i = 1; i <= examsToAdd; i++) {
			String examName = "Exam" + i;
			Exam e = new Exam(0, examName, "Examen", "20-10-2018");
			//service.addExam(e);
		}
		Collection<Exam> examsAfterAdd = service.getExams();
		assertTrue("Manage to add exams", examsAfterAdd.size() == countExams + 3);
	}	

}
