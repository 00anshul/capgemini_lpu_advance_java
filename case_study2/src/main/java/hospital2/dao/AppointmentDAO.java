package hospital2.dao;

import java.util.List;
import javax.persistence.EntityManager;
import hospital2.entities.Appointment;
import hospital2.entities.Doctor;

public class AppointmentDAO {

    private EntityManager em;

    public AppointmentDAO(EntityManager em) {
        this.em = em;
    }

    public void saveAppointment(Appointment appointment) {
        em.persist(appointment);
    }

    public List<Appointment> findAppointmentByDoctor(int doctorId) {
        Doctor doctor = em.find(Doctor.class, doctorId);
        return doctor != null ? doctor.getAppointments() : null;
    }

    public void updateFee(int appointmentId, double newFee) {
        Appointment appointment = em.find(Appointment.class, appointmentId);
        if (appointment != null) {
            appointment.setFee(newFee);   // dirty checking handles the update
        }
    }
}