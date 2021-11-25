package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.entities.Cliente;
import com.entities.Producto;
import com.entities.Venta;
import com.services.ClienteService;
import com.services.ProductoService;
import com.services.VentaService;

@SpringBootTest
public class VentaTests {

	@Autowired
	private VentaService ventaService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void addVenta() {
		Producto p = new Producto("lapiz", 50.0);
		this.productoService.createProducto(p);
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		Venta v = new Venta(p, c);
		Venta v2 = this.ventaService.createVenta(v);
		assertEquals(v, v2);
	}
	
	@Test
	public void getVenta() {
		Producto p = new Producto("lapiz", 50.0);
		this.productoService.createProducto(p);
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		Venta v = new Venta(p, c);
		this.ventaService.createVenta(v);
		assertNotNull(this.ventaService.getVentaById(v.getId()));
	}
	
	@Test
	public void deleteVenta() {
		Producto p = new Producto("lapiz", 50.0);
		this.productoService.createProducto(p);
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		Venta v = new Venta(p, c);
		this.ventaService.createVenta(v);
		assertNotNull(this.ventaService.getVentaById(v.getId()));
		this.ventaService.deleteVenta(v);
		assertFalse(this.ventaService.getVentas().contains(v));
	}
	
	@Test
	public void updateVenta() {
		Producto p = new Producto("lapiz", 50.0);
		this.productoService.createProducto(p);
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		Venta v = new Venta(p, c);
		v = this.ventaService.createVenta(v);
		assertEquals(c, v.getCliente());
		Cliente c2 = new Cliente("Pepe", "Mendez", "87654321");
		this.clienteService.createCliente(c2);
		v.setCliente(c2);
		v = this.ventaService.updateVenta(v);
		assertNotEquals(c, v.getCliente());
	}
	
}
