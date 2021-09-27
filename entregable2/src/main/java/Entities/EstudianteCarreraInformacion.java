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
}
