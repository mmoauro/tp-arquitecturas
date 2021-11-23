package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Sale;
import com.entregable4.entregable4.model.CustomerSaleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("FROM sales WHERE customer.id = :id")
    List<Sale> getCompras(int id);

    @Query("SELECT SUM(p.price), c FROM Customer c INNER JOIN sales v ON c.id = v.customer.id INNER JOIN Product p ON v.product.id = p.id GROUP BY c")
    List<CustomerSaleDTO> getClientesConMonto();

    @Query("FROM Customer c WHERE c.dni = :dni")
    Customer getCustomerByDni(String dni);




}
