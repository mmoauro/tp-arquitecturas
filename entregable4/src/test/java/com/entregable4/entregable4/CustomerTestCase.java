package com.entregable4.entregable4;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTestCase {
    @Autowired
    private CustomerService customerService;


    @Test
    public Customer createCustomerTestCase() {
        Customer customer = new Customer();
        customer.setName("Manuel");
        customer.setSurname("Moauro");
        customer.setDni("43659071");
        this.customerService.createCustomer(customer);
        Assertions.assertEquals(this.customerService.getCustomerByDni(customer.getDni()), customer);
        return customer;
    }

    @Test
    public void updateCustomerTestCase() {
        Customer customer = this.createCustomerTestCase();
        customer.setDni("123456");
        Customer c = this.customerService.updateCustomer(customer);
        Assertions.assertEquals("123456", c.getDni());
    }

    @Test
    public void getCustomerByIdTestCase() {
        Customer customer = this.createCustomerTestCase();
        Customer c = this.customerService.getCustomerById(customer.getId());
        Assertions.assertEquals(customer, c);
    }

    @Test
    public void deleteCustoemrTestCase() {
        Customer customer = this.createCustomerTestCase();
        this.customerService.deleteCustomer(customer);
        Assertions.assertEquals(false, this.customerService.getCustomers().contains(customer));
    }
}
