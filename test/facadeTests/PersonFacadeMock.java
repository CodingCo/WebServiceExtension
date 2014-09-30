package facadeTests;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;


/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacadeMock implements FacadeInterface {

    private List<Person> persons;

    public PersonFacadeMock() {
        this.persons = new ArrayList<>();
    }
    
    @Override
    public String getPersonsAsJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOnePersonAsJson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person addPersonFromGson(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleSchool addRoleSchool(String json, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
