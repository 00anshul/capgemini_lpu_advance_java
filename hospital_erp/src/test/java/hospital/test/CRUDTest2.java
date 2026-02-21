package hospital.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hospital.entities.Department;
import hospital.entities.Doctor;

public class CRUDTest2 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hospitalERP");
        EntityManager em = emf.createEntityManager();

        // CREATE 
        
        Department dept = new Department();
        dept.setId(1);
        dept.setName("Cardiology");
        dept.setLocation("Block A");

        Doctor d1 = new Doctor();
        d1.setId(1);
        d1.setName("Dr. Mehta");
        d1.setSpecialization("Cardiologist");

        Doctor d2 = new Doctor();
        d2.setId(2);
        d2.setName("Dr. Singh");
        d2.setSpecialization("Neurologist");

        // Setting BOTH sides of the relationship
        d1.setDepartment(dept);           // doctor → department (owning side, writes FK to DB)
        d2.setDepartment(dept);           // doctor → department (owning side, writes FK to DB)
        dept.getDoctors().add(d1);        // department → doctor (inverse, for in-memory navigation)
        dept.getDoctors().add(d2);        // department → doctor (inverse, for in-memory navigation)

        em.getTransaction().begin();
        em.persist(dept);
        em.persist(d1);
        em.persist(d2);
        em.getTransaction().commit();
        System.out.println("SAVED department and doctors");

        // READ — navigate from both sides


        // Department → Doctors
        Department foundDept = em.find(Department.class, 1);
        System.out.println("DEPARTMENT: " + foundDept);
        System.out.println("DOCTORS IN DEPARTMENT:");
        for (Doctor d : foundDept.getDoctors()) {
            System.out.println("  " + d);
        }

        // Doctor → Department
        Doctor foundDoctor = em.find(Doctor.class, 1);
        System.out.println("DOCTOR: " + foundDoctor);
        System.out.println("BELONGS TO: " + foundDoctor.getDepartment());

        // UPDATE

        em.getTransaction().begin();
        foundDept.setLocation("Block B");         // update department
        foundDoctor.setSpecialization("Senior Cardiologist");  // update doctor
        em.getTransaction().commit();
        System.out.println("UPDATED department location and doctor specialization");


        // DELETE — remove one doctor from department

        em.getTransaction().begin();
        Doctor toRemove = em.find(Doctor.class, 2);
        toRemove.setDepartment(null);         // remove FK — unlink from department
        dept.getDoctors().remove(toRemove);   // sync in-memory
        em.remove(toRemove);                  // delete doctor
        em.getTransaction().commit();
        System.out.println("DELETED Dr. Singh");

        em.close();
        emf.close();
    }
}