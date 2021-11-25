package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.entities.Cliente;
import com.services.ClienteService;

import junit.framework.Assert;

@SpringBootTest
public class ClienteTests {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test 
	public void addCliente() {
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		assertEquals(c, this.clienteService.getClienteById(c.getId()));
	}
	
	@Test 
	public void getCliente() {
		Cliente c = new Cliente("Juan", "Perez", "12345678");
		this.clienteService.createCliente(c);
		assertNotNull(this.clienteService.getClienteById(c.getId()));
	}
	
	@Test
	public void deleteCliente() {
		Cliente c = new Cliente("Pepe", "Mendez", "87654321");
		Cliente c2 = this.clienteService.createCliente(c);
		assertNotNull(c2);
		this.clienteService.deleteCliente(c2);
		assertFalse(this.clienteService.getClientes().contains(c2));
	}
	
	@Test
	public void updateCliente() {
		String dni = "87654321";
		Cliente c = new Cliente("Pepe", "Mendez", dni);
		this.clienteService.createCliente(c);
		Cliente c2 = this.clienteService.getClienteByDNI(dni);
		assertEquals(dni, c2.getDni());
		int id = c2.getId();
		c2.setDni("1111111");
		c2 = this.clienteService.updateCliente(c2);
		assertNotEquals(dni, c2.getDni());
	}

}
