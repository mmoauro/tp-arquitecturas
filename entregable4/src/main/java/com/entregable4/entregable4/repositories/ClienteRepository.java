package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Cliente;
import com.entregable4.entregable4.entities.Venta;
import com.entregable4.entregable4.model.ClienteVentaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.List;

@Component("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("FROM ventas WHERE cliente.id = :id")
    List<Venta> getCompras(int id);

    @Query("SELECT SUM(p.precio), c FROM Cliente c INNER JOIN ventas v ON c.id = v.cliente.id INNER JOIN Producto p ON v.producto.id = p.id GROUP BY c")
    List<ClienteVentaDTO> getClientesConMonto();




}
