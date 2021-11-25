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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/clientes")
@Api(value = "CustomerController", description = "Controller for customers")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @ApiOperation(value = "Get a list of all customers", response = List.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("")
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(this.clienteService.getclientes());
    }

    @ApiOperation(value = "Get a customers by id", response = Cliente.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: customer not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") int id) {
    	return ResponseEntity.ok(this.clienteService.getclienteById(id));
    }
    
    @ApiOperation(value = "Create a new customer", response = Cliente.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("")
    public ResponseEntity<Cliente> createCliente(@RequestBody() Cliente cliente) {
        this.clienteService.createcliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Edit a customer", response = Cliente.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: customer not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("")
    public ResponseEntity<Cliente> updateProducto(@RequestBody() Cliente cliente) {
        this.clienteService.updatecliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation(value = "Delete a customers by id", response = String.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: customer not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
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

    @ApiOperation(value = "Get customer purchases list by customer id", response = List.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: customer not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}/compras")
    public ResponseEntity<List<Venta>> getCompras(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.clienteService.getCompras(this.clienteService.getclienteById(id)));
    }
}
