package facadeTests;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.AssistentTeacher;
import model.Person;
import model.RoleSchool;
import model.Student;
import model.Teacher;
import webinterfaces.FacadeInterface;

/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacadeMock implements FacadeInterface {

    private List<Person> persons;
    private Gson trans;

    public PersonFacadeMock() {
        this.persons = new ArrayList<>();
        this.trans = new Gson();
    }

    @Override
    public String getPersonsAsJSON() {
        return trans.toJson(persons);
    }

    @Override
    public String getOnePersonAsJson(long id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return trans.toJson(person);
            }
        }
        return null;
    }

    @Override
    public Person addPersonFromGson(String json) {
        Person personToAdd = trans.fromJson(json, Person.class);
        personToAdd.setId(100000l + (persons.size() + 1));
        persons.add(personToAdd);
        return personToAdd;
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
        if (json.contains("AssistentTeacher")){
            role = trans.fromJson(json, AssistentTeacher.class);
        }
        for (Person person : persons) {
            if (person.getId() == id && role != null) {
                person.addRole(role);
            }
        }
        return role;
    }

    @Override
    public Person delete(long id) {
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getId() == id){
                return persons.remove(i);
            }
        }
        return null;
    }

}
