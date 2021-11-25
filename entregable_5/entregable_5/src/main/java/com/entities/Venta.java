package com.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@Entity(name = "ventas")
public class Venta {
	
	@ApiModelProperty(notes = "Sale id", name = "id", readOnly = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Cliente cliente;
    private Date createdDate;

    public Venta() {
        this.createdDate = new Date();
    }

    public int getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return this.producto.getPrecio();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
