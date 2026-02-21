package hospital2.service;

import java.util.List;
import javax.persistence.EntityManager;
import hospital2.dao.AppointmentDAO;
import hospital2.entities.Appointment;

public class AppointmentService {

    private EntityManager em;
    private AppointmentDAO appointmentDAO;

    public AppointmentService(EntityManager em) {
        this.em = em;
        this.appointmentDAO = new AppointmentDAO(em);
    }

    // update appointment fee
    public void updateFee(int appointmentId, double newFee) {
        em.getTransaction().begin();
        appointmentDAO.updateFee(appointmentId, newFee);
        em.getTransaction().commit();
        System.out.println("Fee updated for appointment: " + appointmentId);
    }

    // fetch appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        List<Appointment> list = appointmentDAO.findAppointmentByDoctor(doctorId);
        System.out.println("Appointments for doctor id " + doctorId + ":");
        for (Appointment a : list) {
            System.out.println("  " + a);
        }
        return list;
    }
}