package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT l FROM Producto l")
    List<Producto> getProductos();
}
