package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author simon
 */
@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facilityIDGen")
    @SequenceGenerator(name = "facilityIDGen", sequenceName = "ROLE_GEN", initialValue = 100000, allocationSize = 1)
    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private String description;

    @OneToMany(mappedBy = "course")
    private Collection<TimeBlock> timeBlock;

    @OneToMany
    @Expose
    private Collection<RoleSchool> roles;

    public Course() {
    }

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "model.Course[ id=" + id + " ]";
    }

    // adders removers
    public void addRole(RoleSchool rs){
	roles.add(rs);
    }
}
