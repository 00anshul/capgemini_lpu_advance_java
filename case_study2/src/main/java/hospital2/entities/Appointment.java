package hospital2.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private int id;
    private LocalDate visitDate;
    private double fee;

    @ManyToOne
    // no @JoinColumn — Hibernate auto names the FK column
    private Patient patient;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    @Override
    public String toString() {
        return "Appointment [id=" + id + ", visitDate=" + visitDate + 
               ", fee=" + fee + ", patient=" + patient + "]";
    }
}