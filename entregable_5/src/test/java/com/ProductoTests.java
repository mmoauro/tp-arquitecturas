package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.entities.Producto;
import com.services.ProductoService;

@SpringBootTest
public class ProductoTests {

	@Autowired
	private ProductoService productoService;
	
	@Test
	public void addProducto() {
		Producto p = new Producto("lapiz", 50.0);
		Producto p2 = this.productoService.createProducto(p);
		assertEquals(p, p2);
	}
	
	@Test
	public void getProducto() {
		Producto p = new Producto("lapiz", 50.0);
		this.productoService.createProducto(p);
		assertNotNull(this.productoService.getProductoById(p.getId()));
	}
	
	@Test
	public void deleteProducto() {
		Producto p = new Producto("lapiz", 50.0);
		Producto p2 = this.productoService.createProducto(p);
		assertNotNull(p2);
		this.productoService.deleteProduct(p2);
		assertFalse(this.productoService.getProductos().contains(p2));
	}
	
	@Test
	public void updateProducto() {
		String name = "lapiz";
		Producto p = new Producto(name, 50.0);
		Producto p2 = this.productoService.createProducto(p);
		assertEquals(name, p2.getNombre());
		p2.setNombre("lapicera");
		this.productoService.updateProducto(p2);
		assertNotEquals(name, p2.getNombre());
	}
	
	
	
}
