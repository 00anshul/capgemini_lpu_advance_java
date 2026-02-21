package university.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    private int id;
    private String semester;
    private String grade;

    @ManyToOne
    // no @JoinColumn — Hibernate auto names FK
    private Course course;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    @Override
    public String toString() {
        return "Enrollment [id=" + id + ", semester=" + semester + 
               ", grade=" + grade + ", course=" + course + "]";
    }
}