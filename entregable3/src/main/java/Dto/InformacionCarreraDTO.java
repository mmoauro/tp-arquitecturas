package Dto;

import Entities.Estudiante;

import java.util.ArrayList;

public class InformacionCarreraDTO {
    private String name;
    private ArrayList<Estudiante> inscriptos;
    private ArrayList<Estudiante> egresados;

    public void setInscriptos(ArrayList<Estudiante> estudiantes) {
        this.inscriptos = estudiantes;
    }

    public void setEgresados(ArrayList<Estudiante> estudiantes) {
        this.egresados = estudiantes;
    }
}
