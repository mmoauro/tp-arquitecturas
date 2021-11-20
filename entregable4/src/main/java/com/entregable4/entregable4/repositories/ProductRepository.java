package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT l FROM Product l")
    List<Product> getProducts();
}
