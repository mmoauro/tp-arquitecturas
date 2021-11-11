package Daos;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DaoStructure {

    protected Session startTransaction() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entregable2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Session session = em.unwrap(Session.class);
        return session;
    }

    protected void closeConnection(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }


}
