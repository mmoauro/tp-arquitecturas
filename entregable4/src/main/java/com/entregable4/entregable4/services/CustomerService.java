package com.entregable4.entregable4.services;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Sell;
import com.entregable4.entregable4.model.CustomerSellDTO;
import com.entregable4.entregable4.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("customerService")
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Transactional
    public List<Customer> getCustomers() {
        return this.repository.findAll();
    }

    @Transactional
    public Customer getCustomerById(int id) {
        return this.repository.getById(id);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        return this.repository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Customer customer) {
        return this.createCustomer(customer);
    }

    @Transactional
    public void deleteCustomer(Customer customer) {
        this.repository.delete(customer);
    }

    @Transactional
    public List<Sell> getCustomerPurchases(Customer customer) {
        return this.repository.getCompras(customer.getId());
    }

    @Transactional
    public List<CustomerSellDTO> getCustomersWithAmountSpend() {
        List<CustomerSellDTO> dtos = new ArrayList<>();

        List<Customer> customers = this.repository.findAll();
        customers.forEach(customer -> {
            List<Sell> sells = this.repository.getCompras(customer.getId());
            double total = sells
                    .stream()
                    .map(Sell::getPrice)
                    .reduce((double) 0, Double::sum);
            CustomerSellDTO dto = new CustomerSellDTO(customer, total);
            dtos.add(dto);
        });
        return dtos;
    }
}
