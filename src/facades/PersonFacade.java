package facades;

import model.Person;
import model.RoleSchool;
import webinterfaces.FacadeInterface;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import model.AssistentTeacher;
import model.Student;
import model.Teacher;

/**
 *
 * @author ThomasHedegaard
 */
public class PersonFacade implements FacadeInterface {

    Gson trans;
    EntityManager em;

    public PersonFacade(Gson trans) {
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
        Person person = em.find(Person.class, id);
        if (person != null && role != null) {
            person.addRole(role);
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

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServerSidePU");
            EntityManager emToReturn = emf.createEntityManager();
            return emToReturn;

        } catch (PersistenceException e) {
            System.err.println("Problem finding Persitence Unit!");
        }

        return null;

    }
    
    @Override
    public Person editPerson(String json, long id){
        Person editedPerson = trans.fromJson(json, Person.class);
        Person personToEdit = em.find(Person.class, id);
        em.getTransaction().begin();
        personToEdit.setFirstName(editedPerson.getFirstName());
        personToEdit.setLastName(editedPerson.getLastName());
        personToEdit.setPhone(editedPerson.getPhone());
        personToEdit.setMail(editedPerson.getMail());
        em.getTransaction().commit();
        return personToEdit;
    }

    @Override
    public RoleSchool deleteRoleSchool(long id, String roleName) {
        String[] roleNameSplit = roleName.split(":");
        String role = roleNameSplit[1].substring(0, roleNameSplit[1].length()-1).trim();
        
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        RoleSchool deletedRole = person.removeRole(role);
        em.getTransaction().commit();
        return deletedRole;
    }

}
