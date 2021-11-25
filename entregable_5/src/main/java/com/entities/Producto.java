package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Producto {
	
	@ApiModelProperty(notes = "Product id", name = "id", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private double precio;

    public Producto() {
    	
    }
    
    public Producto(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return this.id+": "+ this.nombre+ ": "+ this.precio;
    }

	@Override
	public boolean equals(Object obj) {
		try {
			Producto p = (Producto) obj;
			return id == p.id;
		} catch (Exception e) {
			return false;
		}
	}

    
    
}
