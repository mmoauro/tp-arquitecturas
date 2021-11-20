package com.entregable4.entregable4.services;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component("productService")
public class ProductService {
    @Autowired
    private ProductRepository respository;

    @Transactional
    public List<Product> getProducts() {
        return this.respository.getProducts();
    }

    @Transactional
    public Product getProductById(int id) {
        return this.respository.getById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        return this.respository.save(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        return this.createProduct(product);
    }

    @Transactional
    public void deleteProduct(Product product) {
        this.respository.delete(product);
    }
}
