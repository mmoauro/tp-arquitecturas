package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Estudiante {

    @Id
    private int dni;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int edad;
    @Column
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Column
    private String ciudad;
    @Column
    private int numeroLibreta;
    @ManyToMany
    private List<Carrera> carreras;

    public Estudiante(int dni, String nombre, String apellido, int edad, Genero genero, String ciudad, int numeroLibreta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        this.numeroLibreta = numeroLibreta;
        this.carreras = new ArrayList<>();
    }

    public Estudiante() {
        this.carreras = new ArrayList<>();
    }

    public void addCarrera (Carrera carrera) {
        this.carreras.add(carrera);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumeroLibreta() {
        return numeroLibreta;
    }

    public void setNumeroLibreta(int numeroLibreta) {
        this.numeroLibreta = numeroLibreta;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public String toString() {
        return this.dni + "";
    }
}
