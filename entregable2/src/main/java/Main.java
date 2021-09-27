import Entities.Carrera;
import Entities.Estudiante;
import Services.CarreraService;
import Services.EstudianteService;

public class Main {
    public static void main(String[] args) {
        Estudiante morro = new Estudiante(123);
        Carrera c = new Carrera(39);
        EstudianteService estudianteService = new EstudianteService();
        CarreraService carreraService = new CarreraService();
        carreraService.addCarrera(c);
        estudianteService.addEstudiante(morro);
        estudianteService.addCarrera(morro, new Carrera(39));
        Estudiante a = new Estudiante(1);
        estudianteService.addEstudiante(a);
        System.out.println(estudianteService.getEstudiantes("id", "desc"));
    }
}
