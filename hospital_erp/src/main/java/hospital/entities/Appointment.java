package hospital.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private int id;
    private LocalDate appointmentDate;
    private String status;        // scheduled, completed, cancelled
    private String description;

    //For CRUD 4
    //-------------------------------------------------------------------------------
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    // getter and setter:
    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { 
        this.prescription = prescription; 
    }
    //-------------------------------------------------------------------------------

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { 
        this.appointmentDate = appointmentDate; 
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Appointment [id=" + id + ", date=" + appointmentDate + 
               ", status=" + status + ", description=" + description + "]";
    }
}