package com.entregable4.entregable4;

import com.entregable4.entregable4.entities.Customer;
import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.entities.Sale;
import com.entregable4.entregable4.services.CustomerService;
import com.entregable4.entregable4.services.ProductService;
import com.entregable4.entregable4.services.SaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SaleServiceTestCase {
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Test
    public Sale createSaleTestCase() {
        Sale sale = new Sale();
        Customer customer = this.customerService.createCustomer(new Customer());
        Product product = this.productService.createProduct(new Product());
        sale.setCustomer(customer);
        sale.setProduct(product);
        Sale s = this.saleService.createSale(sale);
        Assertions.assertTrue(s.getId() > 0);
        return s;
    }

    @Test
    public void getSaleGroupedByDateTestCase() {
        Sale sale = this.createSaleTestCase();
        sale.setCreatedDate(new Date());
        Map<Date, List<Sale>> results = this.saleService.getSalesGroupedByDate();
        Assertions.assertFalse(results.keySet().isEmpty());
    }
}
