package com.entregable4.entregable4.model;

import com.entregable4.entregable4.entities.Customer;

public class CustomerSaleDTO {
    private Customer customer;
    private double price;

    public CustomerSaleDTO() {

    }

    public CustomerSaleDTO(Customer customer, double price) {
        this.customer = customer;
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return this.getCustomer().getName();
    }

}
