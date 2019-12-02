package com.geekbrains.bootdata.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Company> companies;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Country{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  '}';
    }
}
