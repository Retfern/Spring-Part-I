package com.flamexander.hibernate.market;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    @Column(name = "current_price")
    private int price;

    @Column(name = "number_products")
    private int number;

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                  "id=" + id +
                  ", order=" + order +
                  ", product=" + product +
                  ", price=" + price +
                  ", number=" + number +
                  '}';
    }

    public Orders getOrder() {
        return order;
    }

    public Products getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
