package com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.entities.Producto;

import java.util.List;

@Component("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT l FROM Producto l")
    List<Producto> getProductos();
}
