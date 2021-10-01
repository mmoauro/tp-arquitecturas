package Daos;

import Entities.Carrera;
import Entities.Estudiante;
import Entities.Genero;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao extends DaoStructure {

    public void add(Estudiante estudiante) {
        Session em = this.startTransaction();
        em.saveOrUpdate(estudiante);
        this.closeConnection(em);
    }


    public void update(Estudiante estudiante) {
        Session em = this.startTransaction();
        em.saveOrUpdate(estudiante);
        this.closeConnection(em);
    }

    public ArrayList<Estudiante> getEstudiantes(String orderBy, String order) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM Estudiante e ORDER BY e." + orderBy + " " + order;
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) em.createQuery(jpql, Estudiante.class).getResultList();
        this.closeConnection(em);
        return estudiantes;
    }

    public Estudiante getEstudiante(int numeroLibreta) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM Estudiante e WHERE e.numeroLibreta = :numeroLibreta";

        Estudiante estudiante = em.createQuery(jpql, Estudiante.class)
                .setParameter("numeroLibreta", numeroLibreta)
                .setMaxResults(1)
                .getSingleResult();
        this.closeConnection(em);
        return estudiante;
    }

    public List<Estudiante> getEstudiante(Genero genero) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";

        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) em.createQuery(jpql, Estudiante.class)
                .setParameter("genero", genero)
                .getResultList();
        this.closeConnection(em);
        return estudiantes;
    }

    public ArrayList<Estudiante> getEstudiantes(Carrera carrera, String origen) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM EstudianteCarreraInformacion ec \n" +
                "JOIN Estudiante e ON ec.estudiante = e \n" +
                "WHERE ec.carrera = :carrera AND e.ciudad = :origen";

        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) em.createQuery(jpql, Estudiante.class)
                .setParameter("carrera", carrera)
                .setParameter("origen", origen)
                .getResultList();
        this.closeConnection(em);
        return estudiantes;
    }

    public ArrayList<Estudiante> getEstudiantes(Carrera carrera, boolean seGraduo) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM EstudianteCarreraInformacion ec \n" +
                "JOIN Estudiante e ON ec.estudiante = e \n" +
                "WHERE ec.carrera = :carrera AND ec.seGraduo = :seGraduo \n" +
                "ORDER BY ec.antiguedad";

        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) em.createQuery(jpql, Estudiante.class)
                .setParameter("carrera", carrera)
                .setParameter("seGraduo", seGraduo)
                .getResultList();
        this.closeConnection(em);
        return estudiantes;
    }

}
