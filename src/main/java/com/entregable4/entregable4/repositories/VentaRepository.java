package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Cliente;
import com.entregable4.entregable4.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@Component("ventaRepository")
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("FROM ventas GROUP BY producto ORDER BY COUNT(producto) DESC")
    List<Venta> getProductoMasVendido();

    @Query("SELECT createdDate FROM ventas GROUP BY createdDate")
    List<Date> getDiasDisponibles();

}
