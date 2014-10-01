package facadeTests;

import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.*; // Temp import!
import model.AssistentTeacher;
import model.Student;
import model.Teacher;

/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacadeDB implements FacadeInterface {
    
    Gson trans;
    EntityManager em;
    
    public PersonFacadeDB(Gson trans) {
        this.trans = trans;
        this.em = createEntityManager();
    }
    
    @Override
    public String getPersonsAsJSON() {
        List<Person> result = em.createQuery("SELECT p FROM Person p").getResultList();
        return trans.toJson(result);
    }
    
    @Override
    public String getOnePersonAsJson(long id) {
        Query query = em.createQuery("SELECT p FROM Person p WHERE p.id = ?1").setParameter(1, id);
        Person person = (Person) query.getSingleResult();
        return trans.toJson(person);
    }
    
    @Override
    public Person addPersonFromGson(String json) {
        Person personFromJson = trans.fromJson(json, Person.class);
        em.getTransaction().begin();
        em.persist(personFromJson);
        em.getTransaction().commit();
        return personFromJson;
    }
    
    @Override
    public RoleSchool addRoleSchool(String json, long id) {
        RoleSchool role = null;
        if (json.contains("Teacher")) {
            role = trans.fromJson(json, Teacher.class);
        }
        if (json.contains("Student")) {
            role = trans.fromJson(json, Student.class);
        }
        if (json.contains("AssistentTeacher")) {
            role = trans.fromJson(json, AssistentTeacher.class);
        }
        
        em.getTransaction().begin();
        Person person = em.find(Person.class,id);
        if(person != null && role != null){
            person.addRole(role);
            //== Maybe use persist, or merge
        }
        em.getTransaction().commit();
        
        return role;
    }
    
    @Override
    public Person delete(long id) {
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        em.remove(person);
        em.getTransaction().commit();
        return person;
    }
    
    private EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("noPercistenceYet");
        EntityManager emToReturn = emf.createEntityManager();
        return emToReturn;
    }
    
}
