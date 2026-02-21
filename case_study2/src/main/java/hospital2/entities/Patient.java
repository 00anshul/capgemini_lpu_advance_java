package hospital2.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    private int id;
    private String name;
    private int age;
    private String contact;

    @OneToOne(cascade = CascadeType.ALL)
    // no @JoinColumn — Hibernate auto names the FK column
    private MedicalRecord medicalRecord;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + 
               ", age=" + age + ", contact=" + contact + 
               ", medicalRecord=" + medicalRecord + "]";
    }
}