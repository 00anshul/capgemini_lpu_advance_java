package hospital.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    private int id;
    private String medicines;
    private String dosage;
    private LocalDate issuedDate;
    private boolean isActive;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMedicines() { return medicines; }
    public void setMedicines(String medicines) { this.medicines = medicines; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    @Override
    public String toString() {
        return "Prescription [id=" + id + ", medicines=" + medicines + 
               ", dosage=" + dosage + ", issuedDate=" + issuedDate + 
               ", isActive=" + isActive + "]";
    }
}