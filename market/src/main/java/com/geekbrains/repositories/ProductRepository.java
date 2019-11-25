package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import com.geekbrains.entities.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {

    private List<Product> productList;
    private Long currentID;

    @PostConstruct
    public void initList () {
        productList = new ArrayList<Product>();
        productList.add(new Product(1L, "bread", new BigDecimal(32.5)));
        productList.add(new Product(2L, "cheese", new BigDecimal(150)));
        productList.add(new Product(3L, "lemon", new BigDecimal(20.5)));
        productList.add(new Product(4L, "tea", new BigDecimal(99)));
        this.currentID=4L;
    }

    public void addNewProduct (String name, BigDecimal price) {
        productList.add(new Product(++currentID, name, price));
        System.out.println("currentID " + currentID);
    }

    public Product addNewProductForm (Product product) {
        if (product.getId()!=null) {
            reset(product);
        } else {
            product.setId(++currentID);
            productList.add(product);
        }
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Optional<Product> getProductByID (Long id) {
        return Optional.of(searchProductID(id));
    }


    public Product searchProductID(Long id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    private void reset (Product product) {
        productList.remove(searchProductID(product.getId()));
        productList.add(product);
    }
}
