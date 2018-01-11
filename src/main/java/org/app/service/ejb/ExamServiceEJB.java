package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EntityBase;
import org.app.service.entities.Exam;
import org.app.service.entities.Test;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("/service") // http://localhost:8080/SAM1/rest/service
// 1. Remote interface
@Stateless
@LocalBean
public class ExamServiceEJB implements ExamService{
	private static Logger logger = Logger.getLogger(TestServiceEJB.class.getName());
	
	// 2. Inject resource 
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

    // 3. Init with injected EntityManager
	private EntityRepository<EntityBase> entityRepository;
	
    @PostConstruct
	public void init(){
		logger.info("Initialized :");
	}	
    public String sayRest() {
    	return "Test Services is On";
    }

	@Override
	public Exam addExam(Exam exam) {
		em.persist(exam);
		em.flush();
		em.refresh(exam);
		return exam;
	}
	@Override
	public String removeExam(Exam exam) {
		exam = em.merge(exam);
		em.remove(exam);
		em.flush();
		return "True";
	}
	
	@Override
	public Exam getExamById(int id) {
		return em.createQuery("SELECT t FROM Test WHERE t.TestId = :testID", Exam.class)
				.setParameter("testID", id)
				.getSingleResult();
	}
	
	@Override
	public Collection<Exam> getExams() {
		List<Exam> exams =  em.createQuery("SELECT t FROM Test", Exam.class).getResultList();
		return exams;
	}	

}
