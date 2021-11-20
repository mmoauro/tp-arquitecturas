package com.entregable4.entregable4.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "sells")
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Customer customer;
    private Date createdDate;

    public Sell() {
        this.createdDate = new Date();
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return this.product.getPrice();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
