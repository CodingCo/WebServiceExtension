package webinterfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.BusinessAcademy;
import model.ClassRoom;
import model.Course;
import model.Person;
import model.RoleSchool;
import model.Student;
import model.TimeBlock;

/**
 *
 * @author simon
 */
public class JPAExecutor {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServerSidePU");
        EntityManager em = emf.createEntityManager();

        RoleSchool s = new Student("Third");
        Person p = new Person("Robert", "Ingeman", "1337", "jegbabbernårjeger@høj", s);
        ClassRoom c = new ClassRoom("2", 14);
        Course co = new Course("programming", "Awesomeness");
        BusinessAcademy ba = new BusinessAcademy("The G5 Academy");
        ba.addClassRooms(c);
        ba.addCourses(co);
        ba.addPerson(p);

        TimeBlock time = new TimeBlock();
        Calendar cal = new GregorianCalendar(1, 2, 4);
        Date date = cal.getTime();
        Calendar cal2 = new GregorianCalendar(4, 3, 10);

        time.setDate(date);
        time.setEndTime(cal);
        time.setStartTime(cal2);
        time.setCourse(co);

        em.getTransaction().begin();
        em.persist(s);
        em.persist(p);
        em.persist(c);
        em.persist(co);
        em.persist(ba);
        em.persist(time);
        em.getTransaction().commit();

    }

}
