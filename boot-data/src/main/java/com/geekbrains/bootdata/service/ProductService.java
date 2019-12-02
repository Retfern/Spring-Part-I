package com.geekbrains.bootdata.service;

import com.geekbrains.bootdata.entities.Company;
import com.geekbrains.bootdata.entities.Country;
import com.geekbrains.bootdata.entities.FilterProduct;
import com.geekbrains.bootdata.entities.Product;
import com.geekbrains.bootdata.repositories.CompanyRepository;
import com.geekbrains.bootdata.repositories.CountryRepository;
import com.geekbrains.bootdata.repositories.ProductRepository;
import com.geekbrains.bootdata.repositories.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Country> findAllCountry () {
        return countryRepository.findAll();
    }

    public List<Company> findAllCompany () {
        return companyRepository.findAll();
    }

    public List<String> countryName () {
        List<String> countries = new ArrayList<>();
        for (Country c: findAllCountry()) {
            countries.add(c.getName());
        }
        return countries;
    }

    public List<String> companyName () {
        List<String> company = new ArrayList<>();
        for (Company c: findAllCompany()) {
            company.add(c.getName());
        }
        return company;
    }

    public Page<Product> findAllProduct(Specification<Product> spec, PageRequest pageRequest) {
        return productRepository.findAll(spec, pageRequest);
    }

    public Page<Product> findAllProduct(FilterProduct filterProduct, PageRequest pageRequest) {
        return productRepository.findAll(getProductFilter(filterProduct), pageRequest);
    }

    public Specification<Product> getProductFilter (FilterProduct filterProduct) {
        Specification<Product> spec = Specification.where(null);
        if (filterProduct.getMinPrice() != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(filterProduct.getMinPrice()));
        }
        if (filterProduct.getMaxPrice() != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(filterProduct.getMaxPrice()));
        }

        Specification<Product> specCompany = Specification.where(null);
        if (filterProduct.getCompanies()!=null) {
            for (String c: filterProduct.getCompanies()) {
                specCompany = specCompany.or(ProductSpecifications.titleContainsCompanyName(c));
            }
            spec = Specification.where(spec).and(specCompany);
        }

        Specification<Product> specCountry = Specification.where(null);
        if (filterProduct.getCountries()!=null) {
            for (String c: filterProduct.getCountries()) {
                specCountry = specCountry.or(ProductSpecifications.titleContainsCountryName(c));
            }
            spec = Specification.where(spec).and(specCountry);
        }

        return spec;
    }



}
