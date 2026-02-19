package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("postgres");

    public static EntityManagerFactory getFactory() {
        return emf;
    }
}
