package Daos;

import Entities.Carrera;
import Entities.Estudiante;
import Entities.EstudianteCarreraInformacion;
import org.hibernate.Session;

import java.util.ArrayList;

public class EstudianteCarreraInformacionDao extends DaoStructure {



    public void addECInformacion(EstudianteCarreraInformacion estudianteCarreraInformacion) {
        Session em = this.startTransaction();
        em.saveOrUpdate(estudianteCarreraInformacion);
        this.closeConnection(em);
    }

    public void updateECInformacion(EstudianteCarreraInformacion estudianteCarreraInformacion) {
        Session em = this.startTransaction();
        em.update(estudianteCarreraInformacion);
        this.closeConnection(em);
    }

    public EstudianteCarreraInformacion getECInformacion(Estudiante estudiante, Carrera carrera) {
        Session em = this.startTransaction();
        String jpql = "SELECT e FROM EstudianteCarreraInformacion e WHERE e.estudiante = :estudiante AND e.carrera = :carrera";

        EstudianteCarreraInformacion eci = em.createQuery(jpql, EstudianteCarreraInformacion.class)
                .setParameter("estudiante", estudiante).setParameter("carrera", carrera).getSingleResult();
        this.closeConnection(em);
        return eci;
    }


}
