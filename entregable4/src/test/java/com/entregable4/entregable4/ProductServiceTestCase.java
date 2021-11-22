package com.entregable4.entregable4;

import com.entregable4.entregable4.entities.Product;
import com.entregable4.entregable4.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTestCase {
    @Autowired
    private ProductService productService;

    @Test
    public Product createProductTestCase () {
        Product product = new Product();
        product.setName("Test");
        product.setPrice(1000);
        return this.productService.createProduct(product);
    }

    @Test
    public void getProductByIdTestCase() {
        Product product = this.createProductTestCase();
        Product p = this.productService.getProductById(product.getId());
        Assertions.assertTrue(product.equals(p));
    }

    @Test
    public void updateProductTestCase() {
        Product product = this.createProductTestCase();
        product.setPrice(123);
        product.setName("Test2");
        this.productService.updateProduct(product);
        Product p = this.productService.getProductById(product.getId());
        Assertions.assertEquals(product.getName(), p.getName());
        Assertions.assertEquals(product.getPrice(), p.getPrice());
    }

    @Test
    public void deleteProductTestCase() {
        Product product = this.createProductTestCase();
        this.productService.deleteProduct(product);
        Assertions.assertFalse(this.productService.getProducts().contains(product));
    }
}
