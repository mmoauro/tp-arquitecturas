package com.entregable4.entregable4.services;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.entities.Sale;
import com.entregable4.entregable4.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component("saleService")
public class SaleService {
    @Autowired
    private SaleRepository repository;

    @Transactional
    public Sale createSale(Sale sale) {
        return this.repository.save(sale);
    }

    public Product getMostSoldProduct() {
        return this.repository.getMostSoldProducts().stream().findFirst().get().getProduct();
    }

    public HashMap<Date, List<Sale>> getSalesGroupedByDate() {
        HashMap<Date, List<Sale>> sales = new HashMap<>();
        List<Date> availableDays = this.repository.getAvailableDays();
        List<Sale> ventas = this.repository.findAll();
        availableDays.forEach(day -> {
            sales.put(day, ventas.stream().filter(sale -> sale.getCreatedDate().equals(day)).collect(Collectors.toList()));
        });
        // Te traes los date disponibles
        // Los metes en el map
        // Metes en date ventas para ese date;
        return sales;
    }
}
