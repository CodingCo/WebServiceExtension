package webinterfaces;

import model.Person;
import model.RoleSchool;

/**
 *
 * @author simon
 */
public interface FacadeInterface {

    public String getPersonsAsJSON();

    public String getOnePersonAsJson(long id);

    public Person addPersonFromGson(String json);

    public RoleSchool addRoleSchool(String json, long id);

    public Person delete(long id);
    
    public Person editPerson(String json, long id);

}
