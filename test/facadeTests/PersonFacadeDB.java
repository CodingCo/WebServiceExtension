package facadeTests;

import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        return null;
    }

    @Override
    public String getOnePersonAsJson(long id) {
        return null;
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
