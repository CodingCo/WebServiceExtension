package facadeTests;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import model.BusinessAcademy;
import model.Person;
import model.Student;
import webinterfaces.AcademyFacadeInterface;

/**
 *
 * @author simon
 */
public class AcademyFacadeMock implements AcademyFacadeInterface {

    List<BusinessAcademy> academys;

    public AcademyFacadeMock() {
        academys = new ArrayList();
        BusinessAcademy a = new BusinessAcademy("CPH");
        a.addPerson(new Person("John", "Doe", "1337", "G5@mail.com", new Student("third")));
        academys.add(a);
        academys.add(new BusinessAcademy("Fort Bregan"));
        academys.add(new BusinessAcademy("Michigan institute of science"));
        academys.add(new BusinessAcademy("The shizzle"));
    }

    @Override
    public String getAcademysAsJson() {
        return new Gson().toJson(academys);
    }

    @Override
    public BusinessAcademy addAcademyFromJson(String json) {
        BusinessAcademy a = new Gson().fromJson(json, BusinessAcademy.class);
        academys.add(a);
        return a;
    }

    @Override
    public BusinessAcademy deleteAcademy(String nameId) {
        for (BusinessAcademy academy : academys) {
            if (academy.getName().equals(nameId)) {
                academys.remove(academy);
                return academy;
            }
        }
        return null;
    }

}
