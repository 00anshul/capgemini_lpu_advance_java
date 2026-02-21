package hospital.test;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hospital.entities.Appointment;
import hospital.entities.Doctor;

public class CRUDTest3 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalERP");
        EntityManager em = emf.createEntityManager();

        // CREATE

        Appointment a1 = new Appointment();
        a1.setId(1);
        a1.setAppointmentDate(LocalDate.of(2026, 2, 21));
        a1.setStatus("Scheduled");
        a1.setDescription("Routine checkup");

        Appointment a2 = new Appointment();
        a2.setId(2);
        a2.setAppointmentDate(LocalDate.of(2026, 2, 22));
        a2.setStatus("Scheduled");
        a2.setDescription("Follow up");

        // Fetch existing doctor from Task 2
        // or create a fresh one
        Doctor doctor = new Doctor();
        doctor.setId(3);
        doctor.setName("Dr. Kapoor");
        doctor.setSpecialization("General Physician");

        // Only set from Doctor side — unidirectional
        doctor.getAppointments().add(a1);
        doctor.getAppointments().add(a2);

        em.getTransaction().begin();
        em.persist(doctor);       // cascades to a1 and a2 automatically
        em.getTransaction().commit();
        System.out.println("SAVED doctor with appointments");

        // READ — only from Doctor side

        Doctor foundDoctor = em.find(Doctor.class, 3);
        System.out.println("DOCTOR: " + foundDoctor);
        System.out.println("APPOINTMENTS:");
        for (Appointment a : foundDoctor.getAppointments()) {
            System.out.println("  " + a);
        }

        // UPDATE
        
        em.getTransaction().begin();
        foundDoctor.getAppointments().get(0).setStatus("Completed");  // update first appointment
        em.getTransaction().commit();
        System.out.println("UPDATED first appointment status to Completed");

        // DELETE — remove one appointment

        em.getTransaction().begin();
        Appointment toRemove = foundDoctor.getAppointments().get(1);
        foundDoctor.getAppointments().remove(toRemove);  // remove from doctor's list
        em.remove(toRemove);                             // delete from DB
        em.getTransaction().commit();
        System.out.println("DELETED second appointment");

        // verify only one appointment remains
        Doctor verify = em.find(Doctor.class, 3);
        System.out.println("REMAINING APPOINTMENTS: " + verify.getAppointments().size());

        em.close();
        emf.close();
    }
}