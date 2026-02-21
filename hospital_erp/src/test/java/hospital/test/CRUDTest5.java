package hospital.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hospital.entities.Doctor;
import hospital.entities.Patient;

public class CRUDTest5 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalERP");
        EntityManager em = emf.createEntityManager();

        // ─────────────────────────────────────
        // STEP 1 — CREATE Doctors and Patients
        // ─────────────────────────────────────
        Doctor mehta = new Doctor();
        mehta.setId(4);
        mehta.setName("Dr. Mehta");
        mehta.setSpecialization("Cardiology");

        Doctor singh = new Doctor();
        singh.setId(5);
        singh.setName("Dr. Singh");
        singh.setSpecialization("Neurology");

        Patient ali = new Patient();
        ali.setId(2);
        ali.setName("Ali");
        ali.setAge(30);
        ali.setGender("Male");

        Patient priya = new Patient();
        priya.setId(3);
        priya.setName("Priya");
        priya.setAge(25);
        priya.setGender("Female");

        Patient raj = new Patient();
        raj.setId(4);
        raj.setName("Raj");
        raj.setAge(35);
        raj.setGender("Male");

        // ─────────────────────────────────────
        // STEP 2 — Assign using helper methods
        // Ali → Mehta + Singh
        // Priya → Mehta
        // Raj → Singh
        // ─────────────────────────────────────
        mehta.addPatient(ali);
        mehta.addPatient(priya);
        singh.addPatient(ali);
        singh.addPatient(raj);

        em.getTransaction().begin();
        em.persist(mehta);        // cascades PERSIST to ali and priya
        em.persist(singh);        // cascades PERSIST to raj (ali already persisted)
        em.getTransaction().commit();
        System.out.println("SAVED — check patient_doctors table, should have 4 rows");

        // ─────────────────────────────────────
        // STEP 3 — Query patients of a doctor (JPQL)
        // ─────────────────────────────────────
        System.out.println("\n--- Patients of Dr. Mehta ---");
        Doctor mehtaFound = em.createQuery(
            "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.name = :n", Doctor.class)
            .setParameter("n", "Dr. Mehta")
            .getSingleResult();

        for (Patient p : mehtaFound.getPatients()) {
            System.out.println("  " + p);
        }

        // Query reverse — all doctors treating Ali
        System.out.println("\n--- Doctors treating Ali ---");
        Patient aliFound = em.createQuery(
            "SELECT p FROM Patient p JOIN FETCH p.doctors WHERE p.name = :n", Patient.class)
            .setParameter("n", "Ali")
            .getSingleResult();

        for (Doctor d : aliFound.getDoctors()) {
            System.out.println("  " + d);
        }

        // ─────────────────────────────────────
        // STEP 4 — Discharge Ali from Dr. Mehta
        // ─────────────────────────────────────
        System.out.println("\n--- Discharging Ali from Dr. Mehta ---");
        em.getTransaction().begin();

        Doctor mehtaManaged = em.find(Doctor.class, 4);
        Patient aliManaged = em.find(Patient.class, 2);
        mehtaManaged.removePatient(aliManaged);   // removes from both sides

        em.getTransaction().commit();
        System.out.println("DISCHARGED — row removed from patient_doctors");
        System.out.println("Ali still exists: " + em.find(Patient.class, 2));
        System.out.println("Dr. Mehta still exists: " + em.find(Doctor.class, 4));

        // ─────────────────────────────────────
        // STEP 5 — LazyInitializationException Demo + Fix
        // ─────────────────────────────────────
        System.out.println("\n--- LazyInitializationException Demo ---");

        // close em to end session
        em.close();

        // try to access lazy collection outside session — this will throw exception
        try {
            mehtaManaged.getPatients().size();   // session is closed — BOOM
        } catch (Exception e) {
            System.out.println("EXCEPTION CAUGHT: " + e.getClass().getSimpleName());
            System.out.println("REASON: " + e.getMessage());
        }

        // Fix — open new session and use JOIN FETCH
        System.out.println("\n--- Fix using JOIN FETCH ---");
        EntityManager em2 = emf.createEntityManager();

        Doctor mehtaFresh = em2.createQuery(
            "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.id = :id", Doctor.class)
            .setParameter("id", 4)
            .getSingleResult();

        System.out.println("PATIENTS OF DR. MEHTA AFTER DISCHARGE:");
        for (Patient p : mehtaFresh.getPatients()) {
            System.out.println("  " + p);
        }

        // ─────────────────────────────────────
        // STEP 6 — Cascade Danger Experiment
        // ─────────────────────────────────────
        System.out.println("\n--- Cascade Danger Experiment ---");
        System.out.println("If CascadeType.REMOVE was added to @ManyToMany on Doctor:");
        System.out.println("Deleting Dr. Singh would also DELETE Ali and Raj from Patient table");
        System.out.println("But Ali is shared — Dr. Mehta would lose a patient too");
        System.out.println("This is why CascadeType.REMOVE is DANGEROUS on Many-to-Many");
        System.out.println("We only use CascadeType.PERSIST and CascadeType.MERGE here");

        em2.close();
        emf.close();
    }
}