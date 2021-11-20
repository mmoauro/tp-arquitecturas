package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.entities.Sell;
import com.entregable4.entregable4.model.CustomerSellDTO;
import com.entregable4.entregable4.services.CustomerService;
import com.entregable4.entregable4.services.SellService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/sells")
public class SellController {
    @Autowired
    private SellService sellService;
    @Autowired
    private CustomerService customerService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/totals")
    public ResponseEntity<List<CustomerSellDTO>> getCustomersWithAmountSpend() {
        return ResponseEntity.ok(this.customerService.getCustomersWithAmountSpend());
    }

    @PostMapping()
    public ResponseEntity<Sell> createSell(@RequestBody() Sell sell) {
        return ResponseEntity.ok(this.sellService.createSell(sell));
    }

    @GetMapping("/most-sold")
    public ResponseEntity<Product> getMostSoldProduct() {
        return ResponseEntity.ok(this.sellService.getMostSoldProduct());
    }

    @GetMapping("/by-day")
    public ResponseEntity<HashMap<Date, List<Sell>>> getSellsGroupedByDate() {
        return ResponseEntity.ok(this.sellService.getSellsGroupedByDate());
    }

}
