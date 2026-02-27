package fintech.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // single factory shared by all DAOs — created once at startup
    private static EntityManagerFactory emf;

    // static block runs once when class is first loaded
    static {
        try {
            emf = Persistence.createEntityManagerFactory("fintech-pu");
            System.out.println("EntityManagerFactory created successfully");
        } catch (Exception e) {
            System.out.println("Failed to create EntityManagerFactory: " + e.getMessage());
            throw e;
        }
    }

    // each DAO method calls this to get a fresh EntityManager
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // called once when application exits
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory closed");
        }
    }
}