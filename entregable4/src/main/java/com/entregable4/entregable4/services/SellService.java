package com.entregable4.entregable4.services;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.entities.Sell;
import com.entregable4.entregable4.repositories.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component("ventaService")
public class SellService {
    @Autowired
    private SellRepository repository;

    @Transactional
    public Sell createSell(Sell sell) {
        return this.repository.save(sell);
    }

    public Product getMostSoldProduct() {
        return this.repository.getMostSoldProducts().stream().findFirst().get().getProduct();
    }

    public HashMap<Date, List<Sell>> getSellsGroupedByDate() {
        HashMap<Date, List<Sell>> sells = new HashMap<>();
        List<Date> availableDays = this.repository.getAvailableDays();
        List<Sell> ventas = this.repository.findAll();
        availableDays.forEach(day -> {
            sells.put(day, ventas.stream().filter(sell -> sell.getCreatedDate().equals(day)).collect(Collectors.toList()));
        });
        // Te traes los date disponibles
        // Los metes en el map
        // Metes en date ventas para ese date;
        return sells;
    }
}
