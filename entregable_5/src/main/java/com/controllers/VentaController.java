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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/ventas")
@Api(value = "SalesController", description = "Controller for sales")
public class VentaController {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;

    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @ApiOperation(value = "Get a list of customers with their amount", response = List.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/totales")
    public ResponseEntity<List<ClienteVentaDTO>> getClientesConMonto() {
        return ResponseEntity.ok(this.clienteService.getClientesConMonto());
    }

    @ApiOperation(value = "Create a sale", response = Venta.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("")
    public ResponseEntity<Venta> createVenta(@RequestBody() Venta venta) {
        return ResponseEntity.ok(this.ventaService.createVenta(venta));
    }

    @ApiOperation(value = "Get the best-selling product", response = Producto.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/mas-vendido")
    public ResponseEntity<Producto> getProductoMasVendido() {
        return ResponseEntity.ok(this.ventaService.getProductoMasVendido());
    }

    @ApiOperation(value = "Get a list of sales grouped by day", response = HashMap.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/por-dia")
    public ResponseEntity<HashMap<Date, List<Venta>>> getVentasAgrupadasPorDia() {
        return ResponseEntity.ok(this.ventaService.getVentasAgrupadasPorFecha());
    }
    
    @ApiOperation(value = "Get a list of all sales", response = List.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("")
    public ResponseEntity<List<Venta>> getVentas() {
    	System.out.println(this.ventaService.getVentas());
        return ResponseEntity.ok(this.ventaService.getVentas());
    }
    
    @ApiOperation(value = "Delete a sale by id", response = String.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: sale not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
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
