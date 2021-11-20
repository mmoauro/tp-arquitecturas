package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Sell;
import com.entregable4.entregable4.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customerService.getCustomers());
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.customerService.getCustomerById(id));
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody() Customer customer) {
        this.customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("")
    public ResponseEntity<Customer> updateCustomer(@RequestBody() Customer customer) {
        this.customerService.updateCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id) {
        try {
            Customer customer = this.customerService.getCustomerById(id);
            logger.info(customer.toString());
            this.customerService.deleteCustomer(customer);
            return ResponseEntity.ok("Cliente eliminado.");
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("No existe ningun cliente con ese id.");
        }
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Sell>> getPurchases(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.customerService.getCustomerPurchases(this.customerService.getCustomerById(id)));
    }
}
