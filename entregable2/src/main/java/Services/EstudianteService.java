package Services;

import Daos.EstudianteDao;
import Entities.Carrera;
import Entities.Estudiante;

import java.util.ArrayList;

public class EstudianteService {
    private EstudianteDao dao;

    public EstudianteService() {
        this.dao = new EstudianteDao();
    }

    public void addEstudiante(Estudiante e) {
        this.dao.add(e);
    }

    public void addCarrera(Estudiante e, Carrera c) {
        e.addCarrera(c);
        this.dao.update(e);
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
