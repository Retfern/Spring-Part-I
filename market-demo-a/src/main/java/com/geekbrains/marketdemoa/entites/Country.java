package com.geekbrains.marketdemoa.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Data
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
}
