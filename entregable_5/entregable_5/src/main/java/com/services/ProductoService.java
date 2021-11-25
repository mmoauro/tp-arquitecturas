package com.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Producto;
import com.repositories.ProductoRepository;

import java.util.List;


@Component("productoService")
public class ProductoService {
    @Autowired
    private ProductoRepository respository;

    @Transactional
    public List<Producto> getProductos() {
        return this.respository.getProductos();
    }

    @Transactional
    public Producto getProductoById(int id) {
        return this.respository.getById(id);
    }

    @Transactional
    public Producto createProducto(Producto producto) {
        return this.respository.save(producto);
    }

    @Transactional
    public Producto updateProducto(Producto producto) {
        return this.createProducto(producto);
    }

    @Transactional
    public void deleteProduct(Producto producto) {
        this.respository.delete(producto);
    }
}
