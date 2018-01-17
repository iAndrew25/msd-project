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
@Path("/students")
@Stateless
@LocalBean
public class StudentServiceEJB 
				extends EntityRepositoryBase<Student>
				implements StudentService{
	private static Logger logger = Logger.getLogger(StudentServiceEJB.class.getName());
	
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
	public Student addStudent(Student student){
		em.persist(student);
		em.flush();
		return student;
	}
    
    //UPDATE
    @PUT
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student updateStudent(Student student){
		em.merge(student);
		em.flush();
		return student;
	}
	
	//Remove
	@DELETE @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public String removeStudent(@PathParam("id") String id){
		String msg="";
		Integer removeId = Integer.valueOf(id);
		try {
		Student student = em.find(Student.class, removeId);
			em.remove(student);
			em.flush();
			msg="Student removed.";
		}
		catch(Exception e){
			msg="No student with the given id has been found. " + "   Error given:   "+e;
		}

		return msg;
	}
	
	//Read
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student getStudentById(@PathParam("id") String id){
		Student e = em.createQuery("SELECT e FROM Student e WHERE e.id = :studentID", Student.class)
				.setParameter("studentID", id)
				.getSingleResult();
		return e;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Student> getStudents(){
		List<Student> students = em.createQuery("SELECT e FROM Student e", Student.class).getResultList();
		return students;
	}	
}