package facades;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import model.Course;
import model.RoleSchool;
import webinterfaces.CourseInterface;

/**
 *
 * @author kasper
 */
public class CourseFacade implements CourseInterface {

    Gson transaction;
    EntityManager em;

    public CourseFacade(Gson transaction) {
	this.transaction = transaction;
	this.em = createEntityManager();
    }

    @Override
    public String getCourse(long id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllCourses() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course addCourse(String json) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course deleteCourse(long id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleSchool addRoleSchoolToCourse(String json, long id) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private EntityManager createEntityManager() {
	try {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServerSidePU");
	    EntityManager emToReturn = emf.createEntityManager();
	    return emToReturn;
	} catch (PersistenceException e) {
	    System.err.println("Problem finding Persistence Unit!");
	}
	return null;
    }

}
