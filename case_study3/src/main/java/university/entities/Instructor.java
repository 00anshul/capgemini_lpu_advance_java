package university.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    private int id;
    private String name;
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    // no @JoinColumn — Hibernate auto names FK
    private InstructorProfile instructorProfile;

    @OneToMany(cascade = CascadeType.ALL)
    // no @JoinColumn — Hibernate creates junction table
    private List<Course> courses = new ArrayList<>();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public InstructorProfile getInstructorProfile() { return instructorProfile; }
    public void setInstructorProfile(InstructorProfile instructorProfile) {
        this.instructorProfile = instructorProfile;
    }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    @Override
    public String toString() {
        return "Instructor [id=" + id + ", name=" + name + 
               ", department=" + department + "]";
    }
}