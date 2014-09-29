package model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author simon
 */
@Entity
public class Student extends RoleSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    private String semester;

    public Student() {
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
