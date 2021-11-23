package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("saleRepository")
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query("FROM sales GROUP BY product ORDER BY COUNT(product) DESC")
    List<Sale> getMostSoldProducts();

    @Query("SELECT createdDate FROM sales GROUP BY createdDate")
    List<Date> getAvailableDays();

}
