package facadeTests;

import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.*; // Temp import!

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
        return null;
    }
    
    @Override
    public RoleSchool addRoleSchool(String json, long id) {
        return null;
    }
    
    @Override
    public Person delete(long id) {
        return null;
    }
    
    private EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("noPercistenceYet");
        EntityManager emToReturn = emf.createEntityManager();
        return emToReturn;
    }
    
}
