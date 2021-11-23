package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.entities.Sale;
import com.entregable4.entregable4.model.CustomerSaleDTO;
import com.entregable4.entregable4.services.CustomerService;
import com.entregable4.entregable4.services.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/totals")
    public ResponseEntity<List<CustomerSaleDTO>> getCustomersWithAmountSpend() {
        return ResponseEntity.ok(this.customerService.getCustomersWithAmountSpend());
    }

    @PostMapping()
    public ResponseEntity<Sale> createsale(@RequestBody() Sale sale) {
        return ResponseEntity.ok(this.saleService.createSale(sale));
    }

    @GetMapping("/most-sold")
    public ResponseEntity<Product> getMostSoldProduct() {
        return ResponseEntity.ok(this.saleService.getMostSoldProduct());
    }

    @GetMapping("/by-day")
    public ResponseEntity<HashMap<Date, List<Sale>>> getsalesGroupedByDate() {
        return ResponseEntity.ok(this.saleService.getSalesGroupedByDate());
    }

}
