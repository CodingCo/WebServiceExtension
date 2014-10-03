package facadeTests;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.BusinessAcademy;
import model.Person;
import model.RoleSchool;
import model.Student;
import webinterfaces.AcademyFacadeInterface;

/**
 *
 * @author simon
 */
public class AcademyFacadeDB implements AcademyFacadeInterface {

    EntityManagerFactory emf;
    EntityManager em;

    public AcademyFacadeDB() {
        emf = Persistence.createEntityManagerFactory("ServerSidePU");
        em = emf.createEntityManager();
        addToDatabase();
    }

    private void addToDatabase() {
        BusinessAcademy a = new BusinessAcademy("CPH");
        RoleSchool s = new Student("third");
        Person p = new Person("John", "Doe", "1337", "G5@mail.com", s);
        a.addPerson(p);
        BusinessAcademy b = new BusinessAcademy("Fort Bregan");
        BusinessAcademy c = new BusinessAcademy("Michigan institute of science");
        BusinessAcademy d = new BusinessAcademy("The shizzle");
        em.getTransaction().begin();
        em.persist(p);
        em.persist(a);
        em.persist(b);
        em.persist(c);
        em.persist(d);
        em.getTransaction().commit();
    }

    @Override
    public String getAcademysAsJson() {
        List<BusinessAcademy> academys = em.createQuery("SELECT a FROM BusinessAcademy a").getResultList();
        return new Gson().toJson(academys);
    }

    @Override
    public BusinessAcademy addAcademyFromJson(String json) {
        BusinessAcademy academy = new Gson().fromJson(json, BusinessAcademy.class);
        em.getTransaction().begin();
        em.persist(academy);
        em.getTransaction().commit();
        return academy;
    }

    @Override
    public BusinessAcademy deleteAcademy(String nameId) {
        BusinessAcademy a = em.find(BusinessAcademy.class, nameId);
        if (a != null) {
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            return a;
        }
        return null;
    }

}
