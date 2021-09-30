package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    private int id;
    @Column
    private String nombre;
    @ManyToMany(mappedBy = "carreras")
    private List<Estudiante> estudiantes;

    public Carrera(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public Carrera() {

    }
}
