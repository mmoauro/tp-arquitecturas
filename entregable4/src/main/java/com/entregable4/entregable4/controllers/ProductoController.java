package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Producto;
import com.entregable4.entregable4.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    private static Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("")
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(this.productoService.getProductos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.productoService.getProductoById(id));
    }

    @PostMapping("")
    public ResponseEntity<Producto> createProduct(@RequestBody() Producto producto) {
        this.productoService.createProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("")
    public ResponseEntity<Producto> updateProducto(@RequestBody() Producto producto) {
        this.productoService.updateProducto(producto);
        return ResponseEntity.ok(producto);
    }

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
