package Daos;

import Entities.Carrera;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class CarreraDao {

    private Session startTransaction() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entregable2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Session session = em.unwrap(Session.class);
        return session;
    }

    private void closeConnection(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    public void add (Carrera carrera) {
        Session em = this.startTransaction();
        em.saveOrUpdate(carrera);
        this.closeConnection(em);
    }

    public void update (Carrera carrera) {
        Session em = this.startTransaction();
        em.saveOrUpdate(carrera);
        this.closeConnection(em);
    }

    public ArrayList<Carrera> getCarrerasWithStudents () {
        Session em = this.startTransaction();
        String jpql = "SELECT C FROM EstudianteCarreraInformacion ec\n" +
                "JOIN Carrera C on ec.carrera = C \n" +
                "GROUP BY ec.carrera HAVING COUNT(ec.carrera) > 0\n" +
                "ORDER BY COUNT(ec.carrera)";
        ArrayList<Carrera> carreras = (ArrayList<Carrera>) em.createQuery(jpql).getResultList();
        this.closeConnection(em);
        return carreras;
    }

    public ArrayList<Carrera> getCarreras() {
        Session em = this.startTransaction();
        String jpql = "SELECT c FROM Carrera c ORDER BY c.nombre";
        ArrayList<Carrera> carreras = (ArrayList<Carrera>) em.createQuery(jpql).getResultList();
        this.closeConnection(em);
        return carreras;
    }
}
