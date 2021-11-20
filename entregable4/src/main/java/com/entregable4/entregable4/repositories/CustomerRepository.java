package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Sell;
import com.entregable4.entregable4.model.CustomerSellDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("FROM sells WHERE customer.id = :id")
    List<Sell> getCompras(int id);

    @Query("SELECT SUM(p.price), c FROM Customer c INNER JOIN sells v ON c.id = v.customer.id INNER JOIN Product p ON v.product.id = p.id GROUP BY c")
    List<CustomerSellDTO> getClientesConMonto();




}
