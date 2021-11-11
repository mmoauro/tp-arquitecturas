package Services;

import Daos.EstudianteCarreraInformacionDao;
import Daos.EstudianteDao;
import Entities.Carrera;
import Entities.Estudiante;
import Entities.EstudianteCarreraInformacion;

import java.util.ArrayList;
import java.util.Date;

public class EstudianteService {
    private EstudianteDao dao;

    public EstudianteService() {
        this.dao = new EstudianteDao();
    }

    public void addEstudiante(Estudiante e) {
        this.dao.add(e);
    }

    public void addCarrera(Estudiante e, Carrera c, int idECI) {
        EstudianteCarreraInformacion eci = new EstudianteCarreraInformacion(idECI, e, c, new Date(), false);
        e.addCarrera(c);
        this.dao.update(e);
        EstudianteCarreraInformacionDao eciDao = new EstudianteCarreraInformacionDao();
        eciDao.addECInformacion(eci);
    }

    public void updateCarrera(EstudianteCarreraInformacion eci, boolean seGraduo) {
        EstudianteCarreraInformacionDao eciDao = new EstudianteCarreraInformacionDao();
        eci.setSeGraduo(seGraduo);
        eciDao.updateECInformacion(eci);
    }

    public EstudianteCarreraInformacion getCarreraInformacion(Estudiante estudiante, Carrera carrera) {
        EstudianteCarreraInformacionDao eciDao = new EstudianteCarreraInformacionDao();
        return eciDao.getECInformacion(estudiante, carrera);
    }

    public ArrayList<Estudiante> getEstudiantes (String orderBy, String order) {
        return this.dao.getEstudiantes(orderBy, order);
    }

    public ArrayList<Estudiante> getEstudiantes (Carrera carrera, String origen) {
        return this.dao.getEstudiantes(carrera, origen);
    }

    public ArrayList<Estudiante> getEstudiantes (Carrera carrera, boolean seGraduo) {
        return this.dao.getEstudiantes(carrera, seGraduo);
    }




}
