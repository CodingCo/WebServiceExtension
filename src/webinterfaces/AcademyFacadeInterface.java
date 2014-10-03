package webinterfaces;

import model.BusinessAcademy;

/**
 *
 * @author simon
 */
public interface AcademyFacadeInterface {

    public String getAcademysAsJson();

    public BusinessAcademy addAcademyFromJson(String json);

    public BusinessAcademy deleteAcademy(String nameId);

}
