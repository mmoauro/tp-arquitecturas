import Entities.Carrera;
import Entities.Estudiante;
import Entities.Genero;
import Services.CarreraService;
import Services.EstudianteService;

public class Main {
    public static void main(String[] args) {
        Carrera c = new Carrera(1, "tudai");
        Carrera c2 = new Carrera(2, "Ing en sistemas");
        Estudiante fefe = new Estudiante(43867576,"fefe","medina",19, Genero.MASCULINO,"TANDIL",72);
        EstudianteService estudianteService = new EstudianteService();
        CarreraService carreraService = new CarreraService();
        carreraService.addCarrera(c);
        carreraService.addCarrera(c2);
        estudianteService.addEstudiante(fefe);
        estudianteService.addCarrera(fefe, c2);
        Estudiante morro = new Estudiante(43867577,"morro","moauro",19, Genero.MASCULINO,"TANDIL",73);
        estudianteService.addEstudiante(morro);
        System.out.println(estudianteService.getEstudiantes("id", "desc"));
    }
}
