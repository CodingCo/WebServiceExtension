package facadeTests;

import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacadeDB implements FacadeInterface {

    Gson trans;

    public PersonFacadeDB(Gson trans) {
        this.trans = trans;
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

}
