package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Cliente;
import com.entregable4.entregable4.entities.Producto;
import com.entregable4.entregable4.entities.Venta;
import com.entregable4.entregable4.model.ClienteVentaDTO;
import com.entregable4.entregable4.services.ClienteService;
import com.entregable4.entregable4.services.VentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
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

}
