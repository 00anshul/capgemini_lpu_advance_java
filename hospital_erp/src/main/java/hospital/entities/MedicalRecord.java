package hospital.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    private int id;
    private String diagnosis;
    private String treatment;
    private String notes;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "MedicalRecord [id=" + id + ", diagnosis=" + diagnosis + 
               ", treatment=" + treatment + ", notes=" + notes + "]";
    }
}