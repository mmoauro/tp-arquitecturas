package com.entregable4.entregable4.repositories;

import com.entregable4.entregable4.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("sellRepository")
public interface SellRepository extends JpaRepository<Sell, Integer> {

    @Query("FROM sells GROUP BY product ORDER BY COUNT(product) DESC")
    List<Sell> getMostSoldProducts();

    @Query("SELECT createdDate FROM sells GROUP BY createdDate")
    List<Date> getAvailableDays();

}
