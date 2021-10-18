package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EstudianteCarreraInformacion {
    @Id
    private int id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Carrera carrera;

    @Column()
    private Date antiguedad;

    @Column
    private boolean seGraduo;

    public EstudianteCarreraInformacion(int id, Estudiante estudiante, Carrera carrera, Date antiguedad, boolean seGraduo) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = antiguedad;
        this.seGraduo = seGraduo;
    }

    public EstudianteCarreraInformacion(int id, Estudiante estudiante, Carrera carrera, boolean seGraduo) {
        this.id = id;
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.seGraduo = seGraduo;
    }

    public EstudianteCarreraInformacion() {

    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public boolean isSeGraduo() {
        return seGraduo;
    }

    public void setSeGraduo(boolean seGraduo) {
        this.seGraduo = seGraduo;
    }
}
