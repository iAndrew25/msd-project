package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.app.patterns.*;
import org.app.service.entities.*;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("/exams")
@Stateless
@LocalBean
public class ExamServiceEJB 
				extends EntityRepositoryBase<Exam>
				implements ExamService{
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
    //CREATE
    @POST
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Exam addExam(Exam exam){
		em.persist(exam);
		em.flush();
		return exam;
	}
    
    //UPDATE
    @PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Exam updateExam(Exam exam){
		em.merge(exam);
		em.flush();
		return exam;
	}
	
	//Remove
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeExam(@PathParam("id") String id){
		String msg="";
		Integer removeId = Integer.valueOf(id);
		try {
		Exam exam=em.find(Exam.class, removeId);
			em.remove(exam);
			em.flush();
			msg="Exam removed.";
		}
		catch(Exception e){
			msg="No exam with the given id has been found. " + "   Error given:   "+e;
		}

		return msg;
	}
	
	//Read
		@Path("/{id}")
		@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
		public Exam getExamById(@PathParam("id") String id){
			Exam e = em.createQuery("SELECT e FROM Exam e WHERE e.id = :examID", Exam.class)
					.setParameter("examID", id)
					.getSingleResult();
			return e;
		}
		
		@GET
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Collection<Exam> getExams(){
			List<Exam> exams = em.createQuery("SELECT e FROM Exam e", Exam.class).getResultList();
			return exams;
		}

		
	
}
