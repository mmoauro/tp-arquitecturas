package com.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entities.Producto;
import com.services.ProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/productos")
@Api(value = "ProductController", description = "Controller for products")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @ApiOperation(value = "Get a list of all products", response = List.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(this.productoService.getProductos());
    }

    @ApiOperation(value = "Get a product by id", response = Producto.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: product not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.productoService.getProductoById(id));
    }

    @ApiOperation(value = "Create a product", response = Producto.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("")
    public ResponseEntity<Producto> createProduct(@RequestBody() Producto producto) {
        this.productoService.createProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @ApiOperation(value = "Edit a product", response = Producto.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: product not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("")
    public ResponseEntity<Producto> updateProducto(@RequestBody() Producto producto) {
        this.productoService.updateProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @ApiOperation(value = "Delete a product by id", response = String.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 404, message = "Error 404: product not found"),
    		@ApiResponse(code = 405, message = "The method is not allowed"),
    		@ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") int id) {
        try {
            Producto producto = this.productoService.getProductoById(id);
            logger.info(producto.toString());
            this.productoService.deleteProduct(producto);
            return ResponseEntity.ok("Producto eliminado.");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No existe ningun producto con ese id.");
        }
    }
}
