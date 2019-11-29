package com.flamexander.hibernate.market;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees_details")
public class UserDetails implements Serializable {
    private static final long serialVersionUID = -3345483994643602633L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "details")
    private Users user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserDetails() {
    }

    public UserDetails(String email, String city, Users user) {
        this.email = email;
        this.city = city;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("UserDetails [id = %d, email = %s, city = %s, user.name = %s]", id, email, city, user.getName());
    }
}
