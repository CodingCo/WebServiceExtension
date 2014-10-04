package webinterfaces;

import model.Person;
import model.RoleSchool;

/**
 *
 * @author simon
 */
public interface PersonFacadeInterface {

    public String getPersonsAsJSON();

    public String getOnePersonAsJson(long id);

    public Person addPersonFromGson(String json);

    public RoleSchool addRoleSchool(String json, long id);

    public Person delete(long id);
    
    public Person editPerson(String json, long id);
    
    public RoleSchool deleteRoleSchool(long id, String roleName);

}
