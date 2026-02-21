package hospital.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    private int id;
    private String name;
    private String specialization;

    @ManyToOne                              // owning side — FK lives here
    @JoinColumn(name = "department_id")     // FK column in doctor table
    private Department department;
    
    //For CRUD 5
    //----------------------------------------------------------------------------
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "patient_doctors",
        joinColumns = {@JoinColumn(name = "doctor_id")},
        inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )
    private List<Patient> patients = new ArrayList<>();

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }

    // helper method — adds patient to both sides
    public void addPatient(Patient p) {
        this.patients.add(p);          // doctor → patient
        p.getDoctors().add(this);      // patient → doctor (sync inverse side)
    }

    // helper method — removes patient from both sides
    public void removePatient(Patient p) {
        this.patients.remove(p);       // doctor → patient
        p.getDoctors().remove(this);   // patient → doctor (sync inverse side)
    }
    //----------------------------------------------------------------------------

    
    //For CRUD 3
    //------------------------------------------------------------------------------

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")       // FK in appointment table
    private List<Appointment> appointments = new ArrayList<>();
    
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { 
        this.appointments = appointments; 
    }
    //------------------------------------------------------------------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    
    
    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + 
               ", specialization=" + specialization + 
               ", department=" + department + "]";
    }
    
}      