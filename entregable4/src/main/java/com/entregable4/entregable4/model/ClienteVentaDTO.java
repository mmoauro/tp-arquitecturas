package com.entregable4.entregable4.model;

import com.entregable4.entregable4.entities.Cliente;

import java.math.BigDecimal;

public class ClienteVentaDTO {
    private Cliente cliente;
    private double monto;

    public ClienteVentaDTO() {

    }

    public ClienteVentaDTO (Cliente cliente, double monto) {
        this.cliente = cliente;
        this.monto = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String toString() {
        return this.getCliente().getNombre();
    }

}
