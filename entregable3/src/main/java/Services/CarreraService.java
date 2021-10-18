package Services;

import Daos.CarreraDao;
import Dto.InformacionCarreraDTO;
import Entities.Carrera;
import Entities.Estudiante;

import java.util.ArrayList;

public class CarreraService {
    private CarreraDao dao;

    public CarreraService() {
        this.dao = new CarreraDao();
    }

    public void addCarrera(Carrera c) {
        this.dao.add(c);
    }

    public ArrayList<Carrera> getCarrerasWithStudents () {
        return this.dao.getCarrerasWithStudents();
    }

    public ArrayList<InformacionCarreraDTO> getCarrerasReport() {
        EstudianteService estudianteService = new EstudianteService();
        ArrayList<InformacionCarreraDTO> dtos = new ArrayList<>();
        ArrayList<Carrera> carreras = this.dao.getCarreras();
        carreras.forEach(c -> {
            InformacionCarreraDTO dto = new InformacionCarreraDTO();
            dto.setEgresados(estudianteService.getEstudiantes(c, true));
            dto.setInscriptos(estudianteService.getEstudiantes(c, false));
            dtos.add(dto);
        });
        return dtos;
    }
}
