package com.entregable4.entregable4.services;

import com.entregable4.entregable4.entities.Cliente;
import com.entregable4.entregable4.entities.Producto;
import com.entregable4.entregable4.entities.Venta;
import com.entregable4.entregable4.repositories.ClienteRepository;
import com.entregable4.entregable4.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component("ventaService")
public class VentaService {
    @Autowired
    private VentaRepository repository;

    @Transactional
    public Venta createVenta(Venta venta) {
        return this.repository.save(venta);
    }

    public Producto getProductoMasVendido() {
        return this.repository.getProductoMasVendido().stream().findFirst().get().getProducto();
    }

    public HashMap<Date, List<Venta>> getVentasAgrupadasPorFecha() {
        HashMap<Date, List<Venta>> ventasAgrupadas = new HashMap<>();
        List<Date> diasDisponibles = this.repository.getDiasDisponibles();
        List<Venta> ventas = this.repository.findAll();
        diasDisponibles.forEach(dia -> {
            ventasAgrupadas.put(dia, ventas.stream().filter(venta -> venta.getCreatedDate().equals(dia)).collect(Collectors.toList()));
        });
        // Te traes los date disponibles
        // Los metes en el map
        // Metes en date ventas para ese date;
        return ventasAgrupadas;
    }
}
