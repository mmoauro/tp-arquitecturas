package com.model;



import java.math.BigDecimal;

import com.entities.Cliente;

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
