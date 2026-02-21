package hospital2.test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import hospital2.entities.Appointment;
import hospital2.entities.Doctor;
import hospital2.entities.MedicalRecord;
import hospital2.entities.Patient;
import hospital2.service.AppointmentService;
import hospital2.service.DoctorService;
import hospital2.service.PatientService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)   // tests run in order we define
public class HospitalTest {

    static EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("postgres");
    
    EntityManager em;
    PatientService patientService;
    DoctorService doctorService;
    AppointmentService appointmentService;

    @BeforeEach   // runs before every test — fresh em each time
    void setup() {
        em = emf.createEntityManager();
        patientService = new PatientService(em);
        doctorService = new DoctorService(em);
        appointmentService = new AppointmentService(em);
    }

    @AfterEach    // runs after every test — close em
    void teardown() {
        if (em.isOpen()) em.close();
    }

    // TEST 1 — OneToOne persist test
    @Test
    @Order(1)
    void testOneToOnePersist() {
        MedicalRecord mr = new MedicalRecord();
        mr.setId(1);
        mr.setBloodGroup("A+");
        mr.setAllergies("Peanuts");

        Patient p = new Patient();
        p.setId(1);
        p.setName("Ali");
        p.setAge(30);
        p.setContact("9876543210");

        patientService.registerPatient(p, mr);

        // verify
        Patient found = patientService.getPatient(1);
        assertNotNull(found);
        assertNotNull(found.getMedicalRecord());
        assertEquals("A+", found.getMedicalRecord().getBloodGroup());
        System.out.println("TEST 1 PASSED — OneToOne persist");
    }

    // TEST 2 — OneToMany persist test
    @Test
    @Order(2)
    void testOneToManyPersist() {
        // create doctor first
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("Dr. Mehta");
        doctor.setSpecialization("Cardiology");
        doctorService.saveDoctor(doctor);

        // get existing patient from test 1
        Patient patient = patientService.getPatient(1);

        // add two appointments
        Appointment a1 = new Appointment();
        a1.setId(1);
        a1.setVisitDate(LocalDate.of(2026, 2, 21));
        a1.setFee(500.0);

        Appointment a2 = new Appointment();
        a2.setId(2);
        a2.setVisitDate(LocalDate.of(2026, 2, 22));
        a2.setFee(750.0);

        doctorService.addAppointmentToDoctor(1, a1, patient);
        doctorService.addAppointmentToDoctor(1, a2, patient);

        // verify
        Doctor found = doctorService.getDoctorWithAppointments(1);
        assertNotNull(found);
        assertEquals(2, found.getAppointments().size());
        System.out.println("TEST 2 PASSED — OneToMany persist");
    }

   // TEST 3 — ManyToOne persist test
    @Test
    @Order(3)
    void testManyToOnePersist() {
        // fetch appointment and verify patient is linked
        Doctor doctor = doctorService.getDoctorWithAppointments(1);
        Appointment appointment = doctor.getAppointments().get(0);

        assertNotNull(appointment.getPatient());
        assertEquals("Ali", appointment.getPatient().getName());
        System.out.println("TEST 3 PASSED — ManyToOne persist");
    }

    // TEST 4 — DAO update test
  @Test
    @Order(4)
    void testDAOUpdate() {
        // update patient contact
        Patient patient = patientService.getPatient(1);
        patient.setContact("1111111111");
        patientService.updatePatient(patient);

        // verify
        Patient updated = patientService.getPatient(1);
        assertEquals("1111111111", updated.getContact());

        // update appointment fee
        appointmentService.updateFee(1, 999.0);
        Doctor doctor = doctorService.getDoctorWithAppointments(1);
        assertEquals(999.0, doctor.getAppointments().get(0).getFee());

        System.out.println("TEST 4 PASSED — DAO update");
    }

    // TEST 5 — DAO delete test
  @Test
  @Order(5)
  void testDAODelete() {
      // first unlink appointments from patient
      em.getTransaction().begin();
      List<Appointment> appointments = em
          .createQuery("SELECT a FROM Appointment a WHERE a.patient.id = :pid", 
                        Appointment.class)
          .setParameter("pid", 1)
          .getResultList();

      for (Appointment a : appointments) {
          a.setPatient(null);
      }
      em.getTransaction().commit();

      // now delete patient safely
      patientService.deletePatient(1);

      Patient deleted = patientService.getPatient(1);
      assertNull(deleted);
      System.out.println("TEST 5 PASSED — DAO delete");
  }
    // TEST 6 — Full service workflow test
    @Test
    @Order(6)
    void testFullServiceWorkflow() {
        // create new patient
        MedicalRecord mr = new MedicalRecord();
        mr.setId(2);
        mr.setBloodGroup("B+");
        mr.setAllergies("None");

        Patient p = new Patient();
        p.setId(2);
        p.setName("Priya");
        p.setAge(25);
        p.setContact("9999999999");

        patientService.registerPatient(p, mr);

        // create doctor
        Doctor doctor = new Doctor();
        doctor.setId(2);
        doctor.setName("Dr. Singh");
        doctor.setSpecialization("Neurology");
        doctorService.saveDoctor(doctor);

        // add appointment
        Appointment a = new Appointment();
        a.setId(3);
        a.setVisitDate(LocalDate.now());
        a.setFee(600.0);
        doctorService.addAppointmentToDoctor(2, a, p);

        // update fee
        appointmentService.updateFee(3, 800.0);

        // verify full flow
        Doctor found = doctorService.getDoctorWithAppointments(2);
        assertEquals(1, found.getAppointments().size());
        assertEquals(800.0, found.getAppointments().get(0).getFee());
        assertEquals("Priya", found.getAppointments().get(0).getPatient().getName());

        System.out.println("TEST 6 PASSED — Full service workflow");
    }
}