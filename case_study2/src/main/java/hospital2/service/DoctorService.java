package hospital2.service;

import java.util.List;
import javax.persistence.EntityManager;
import hospital2.dao.AppointmentDAO;
import hospital2.dao.DoctorDAO;
import hospital2.entities.Appointment;
import hospital2.entities.Doctor;
import hospital2.entities.Patient;

public class DoctorService {

    private EntityManager em;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;

    public DoctorService(EntityManager em) {
        this.em = em;
        this.doctorDAO = new DoctorDAO(em);
        this.appointmentDAO = new AppointmentDAO(em);
    }

    // save doctor first then add appointments
    public void saveDoctor(Doctor doctor) {
        em.getTransaction().begin();
        doctorDAO.saveDoctor(doctor);
        em.getTransaction().commit();
        System.out.println("Doctor saved: " + doctor);
    }

    // add multiple appointments to a doctor
    public void addAppointmentToDoctor(int doctorId, Appointment appointment, Patient patient) {
        em.getTransaction().begin();
        Doctor doctor = doctorDAO.findDoctor(doctorId);
        appointment.setPatient(patient);              // link patient to appointment
        doctor.getAppointments().add(appointment);    // link appointment to doctor
        appointmentDAO.saveAppointment(appointment);  // persist appointment
        em.getTransaction().commit();
        System.out.println("Appointment added to doctor: " + appointment);
    }

    // fetch doctor with all appointments
    public Doctor getDoctorWithAppointments(int doctorId) {
        Doctor doctor = doctorDAO.findDoctor(doctorId);
        System.out.println("Doctor: " + doctor);
        System.out.println("Appointments:");
        for (Appointment a : doctor.getAppointments()) {
            System.out.println("  " + a);
        }
        return doctor;
    }
}