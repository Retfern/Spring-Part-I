package com.geekbrains.services;
import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository (ProductRepository productRepository) {
        this.productRepository=productRepository;
    }

    public List<Product> getListProducts () {
        return productRepository.getProductList();
    }

    public void addNewProduct (String name, BigDecimal price) {
        productRepository.addNewProduct(name, price);
    }

    public Product addNewProductForm (Product product) {
        return productRepository.addNewProductForm(product);
    }

    public Optional<Product> getProductByID (Long id) {
        return productRepository.getProductByID(id);
    }
}
