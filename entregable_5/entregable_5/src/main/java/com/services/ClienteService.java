package com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.*;
import com.model.ClienteVentaDTO;
import com.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Component("clienteService")
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    @Transactional
    public List<Cliente> getclientes() {
        return this.repository.findAll();
    }

    @Transactional
    public Cliente getclienteById(int id) {
        return this.repository.getById(id);
    }

    @Transactional
    public Cliente createcliente(Cliente cliente) {
        return this.repository.save(cliente);
    }

    @Transactional
    public Cliente updatecliente(Cliente cliente) {
        return this.createcliente(cliente);
    }

    @Transactional
    public void deleteProduct(Cliente cliente) {
        this.repository.delete(cliente);
    }

    @Transactional
    public List<Venta> getCompras(Cliente cliente) {
        return this.repository.getCompras(cliente.getId());
    }

    @Transactional
    public List<ClienteVentaDTO> getClientesConMonto() {
        List<ClienteVentaDTO> dtos = new ArrayList<>();

        List<Cliente> clientes = this.repository.findAll();
        clientes.forEach(cliente -> {
            List<Venta> compras = this.repository.getCompras(cliente.getId());
            double total = compras
                    .stream()
                    .map(Venta::getMonto)
                    .reduce((double) 0, Double::sum);
            ClienteVentaDTO dto = new ClienteVentaDTO(cliente, total);
            dtos.add(dto);
        });
        return dtos;
    }
}
