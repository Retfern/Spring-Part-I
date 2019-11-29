package com.flamexander.hibernate.market;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails;

    public Products () {}

    public Products(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, price = %d]", id, title, price);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
