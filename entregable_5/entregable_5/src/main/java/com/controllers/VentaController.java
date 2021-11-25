package com.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entities.Producto;
import com.entities.Venta;
import com.model.ClienteVentaDTO;
import com.services.ClienteService;
import com.services.VentaService;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;

    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("/totales")
    public ResponseEntity<List<ClienteVentaDTO>> getClientesConMonto() {
        return ResponseEntity.ok(this.clienteService.getClientesConMonto());
    }

    @PostMapping("")
    public ResponseEntity<Venta> createVenta(@RequestBody() Venta venta) {
        return ResponseEntity.ok(this.ventaService.createVenta(venta));
    }

    @GetMapping("/mas-vendido")
    public ResponseEntity<Producto> getProductoMasVendido() {
        return ResponseEntity.ok(this.ventaService.getProductoMasVendido());
    }

    @GetMapping("/por-dia")
    public ResponseEntity<HashMap<Date, List<Venta>>> getVentasAgrupadasPorDia() {
        return ResponseEntity.ok(this.ventaService.getVentasAgrupadasPorFecha());
    }
    
    @GetMapping("")
    public ResponseEntity<List<Venta>> getVentas() {
    	System.out.println(this.ventaService.getVentas());
        return ResponseEntity.ok(this.ventaService.getVentas());
    }
    
    /*@GetMapping("")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(this.productoService.getProductos());
    }*/
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable("id") int id) {
        try {
            Venta venta = this.ventaService.getVentaById(id);
            logger.info(venta.toString());
            this.ventaService.deleteVenta(venta);
            return ResponseEntity.ok("Venta eliminada.");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No existe ninguna venta con ese id.");
        }
    }

}
