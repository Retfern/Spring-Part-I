package com.flamexander.hibernate.market;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "details_id")
    private UserDetails details;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    public Users () {}

    public Users(String name) {
        this.name = name;
    }

    public Users(String name, UserDetails details) {
        this.name = name;
        this.details = details;
    }

    @Override
    public String toString() {
        return String.format("Users [id = %d, name = %s]", id, name);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserDetails getDetails() {
        return details;
    }

    public List<Orders> getOrders() {
        return orders;
    }
}
