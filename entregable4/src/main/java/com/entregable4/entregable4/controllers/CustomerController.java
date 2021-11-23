package com.entregable4.entregable4.controllers;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Sale;
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
    /**
     * @return a list with all the customers.
     */
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customerService.getCustomers());
    }

    /**
     * @param id id of the customer.
     * @return the customer with the id given.
     */
    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.customerService.getCustomerById(id));
    }

    /**
     *
     * @param customer customer to be created.
     * @return the customer
     */
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody() Customer customer) {
        this.customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * @param customer customer to be u.pdated.
     * @return the customer
     */
    @PutMapping("")
    public ResponseEntity<Customer> updateCustomer(@RequestBody() Customer customer) {
        this.customerService.updateCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * @param id id of the customer to be deleted.
     * @return a message with the status of the action.
     */
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

    /**
     * @param id id of the customer.
     * @return a list with the purchases of the customer
     */
    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Sale>> getPurchases(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.customerService.getCustomerPurchases(this.customerService.getCustomerById(id)));
    }
}
