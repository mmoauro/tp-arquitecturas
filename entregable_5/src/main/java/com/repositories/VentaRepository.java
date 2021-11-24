package com.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.entities.Producto;
import com.entities.Venta;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@Component("ventaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("FROM ventas GROUP BY producto ORDER BY COUNT(producto) DESC")
    List<Venta> getProductoMasVendido();

    @Query("SELECT createdDate FROM ventas GROUP BY createdDate")
    List<Date> getDiasDisponibles();
    
    @Query("SELECT v FROM ventas v")
    List<Venta> getAllVentas();

}
