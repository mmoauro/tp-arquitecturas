package com.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entities.Cliente;
import com.entities.Venta;
import com.services.ClienteService;

import io.swagger.annotations.Api;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/clientes")
@Api(value = "ClienteController", description = "Controller for customers")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("")
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(this.clienteService.getclientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") int id) {
    	return ResponseEntity.ok(this.clienteService.getclienteById(id));
    }

    @PostMapping("")
    public ResponseEntity<Cliente> createCliente(@RequestBody() Cliente cliente) {
        this.clienteService.createcliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("")
    public ResponseEntity<Cliente> updateProducto(@RequestBody() Cliente cliente) {
        this.clienteService.updatecliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") int id) {
        try {
            Cliente cliente = this.clienteService.getclienteById(id);
            logger.info(cliente.toString());
            this.clienteService.deleteProduct(cliente);
            return ResponseEntity.ok("Cliente eliminado.");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No existe ningun cliente con ese id.");
        }
    }

    @GetMapping("/{id}/compras")
    public ResponseEntity<List<Venta>> getCompras(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.clienteService.getCompras(this.clienteService.getclienteById(id)));
    }
}
