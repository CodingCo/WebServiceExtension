package facades;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import model.BusinessAcademy;
import webinterfaces.AcademyFacadeInterface;

/**
 *
 * @author simon
 */
public class AcademyFacade implements AcademyFacadeInterface {

    Factory fac;
    EntityManager em;

    public AcademyFacade() {
        this.fac = Factory.getInstance();
        this.em = fac.getManager();
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
