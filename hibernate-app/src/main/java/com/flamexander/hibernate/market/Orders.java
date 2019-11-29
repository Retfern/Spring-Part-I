package com.flamexander.hibernate.market;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    public Orders(Users user) {
        this.user = user;
    }

    public Orders () {}


    @Override
    public String toString() {
        return String.format("Users [id = %d, user = %s]", id, user);
    }

    public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
