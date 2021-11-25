package com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Producto;
import com.entities.Venta;
import com.repositories.VentaRepository;

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

    @Transactional
    public Producto getProductoMasVendido() {
        return this.repository.getProductoMasVendido().stream().findFirst().get().getProducto();
    }

    @Transactional
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
    
    @Transactional
    public List<Venta> getVentas() {
    	return this.repository.getAllVentas();	
    }
    
    
    public Venta getVentaById(int id) {
    	return this.repository.getById(id);
    }
    
    @Transactional
    public void deleteVenta(Venta venta) {
    	this.repository.delete(venta);
    }
}
