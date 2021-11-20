package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(this.productService.getProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody() Product product) {
        this.productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody() Product product) {
        this.productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        try {
            Product product = this.productService.getProductById(id);
            logger.info(product.toString());
            this.productService.deleteProduct(product);
            return ResponseEntity.ok("Producto eliminado.");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No existe ningun producto con ese id.");
        }
    }
}
