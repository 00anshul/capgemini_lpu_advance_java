package hospital.test;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hospital.entities.Appointment;
import hospital.entities.Prescription;

public class CRUDTest4 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalERP");
        EntityManager em = emf.createEntityManager();

        // CREATE — Appointment WITH Prescription

        Prescription pr = new Prescription();
        pr.setId(1);
        pr.setMedicines("Paracetamol, Amoxicillin");
        pr.setDosage("500mg twice daily");
        pr.setIssuedDate(LocalDate.of(2026, 2, 21));
        pr.setActive(true);

        Appointment a1 = new Appointment();
        a1.setId(3);
        a1.setAppointmentDate(LocalDate.of(2026, 2, 21));
        a1.setStatus("Completed");
        a1.setDescription("Throat infection");
        a1.setPrescription(pr);              // linked to prescription

        // CREATE — Appointment WITHOUT Prescription

        Appointment a2 = new Appointment();
        a2.setId(4);
        a2.setAppointmentDate(LocalDate.of(2026, 2, 22));
        a2.setStatus("Cancelled");
        a2.setDescription("Patient did not show up");
        a2.setPrescription(null);            // no prescription — optional=true allows this

        em.getTransaction().begin();
        em.persist(a1);                      // cascades to pr automatically
        em.persist(a2);                      // no prescription to cascade
        em.getTransaction().commit();
        System.out.println("SAVED both appointments");

        // READ — null safe check

        Appointment found1 = em.find(Appointment.class, 3);
        Appointment found2 = em.find(Appointment.class, 4);

        System.out.println("\n--- Appointment 1 ---");
        System.out.println(found1);
        if (found1.getPrescription() != null) {
            System.out.println("PRESCRIPTION: " + found1.getPrescription());
        } else {
            System.out.println("PRESCRIPTION: None");
        }

        System.out.println("\n--- Appointment 2 ---");
        System.out.println(found2);
        if (found2.getPrescription() != null) {
            System.out.println("PRESCRIPTION: " + found2.getPrescription());
        } else {
            System.out.println("PRESCRIPTION: None");   // this one prints
        }

        // UPDATE — add prescription to appointment that had none

        em.getTransaction().begin();
        Prescription pr2 = new Prescription();
        pr2.setId(2);
        pr2.setMedicines("Ibuprofen");
        pr2.setDosage("400mg once daily");
        pr2.setIssuedDate(LocalDate.of(2026, 2, 22));
        pr2.setActive(true);

        found2.setStatus("Completed");
        found2.setPrescription(pr2);         // now giving it a prescription
        em.persist(pr2);                     // persist prescription first
        em.getTransaction().commit();
        System.out.println("\nUPDATED appointment 2 — added prescription");

        // verify
        Appointment verify = em.find(Appointment.class, 4);
        System.out.println("PRESCRIPTION NOW: " + verify.getPrescription());

        // DELETE — remove prescription from appointment

        em.getTransaction().begin();
        Appointment toUpdate = em.find(Appointment.class, 3);
        Prescription toDelete = toUpdate.getPrescription();
        toUpdate.setPrescription(null);      // unlink first
        em.remove(toDelete);                 // then delete prescription
        em.getTransaction().commit();
        System.out.println("\nDELETED prescription from appointment 1");

        // verify appointment still exists, prescription is gone
        Appointment finalCheck = em.find(Appointment.class, 3);
        System.out.println("APPOINTMENT STILL EXISTS: " + finalCheck);
        System.out.println("PRESCRIPTION: " + 
            (finalCheck.getPrescription() != null ? finalCheck.getPrescription() : "None"));

        em.close();
        emf.close();
    }
}