package com.geekbrains.bootdata.repositories;

import com.geekbrains.bootdata.entities.Company;
import com.geekbrains.bootdata.entities.Country;
import com.geekbrains.bootdata.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecifications {

    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(int value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }

    public static Specification<Product> priceLesserThanOrEq(int value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
        };
    }

    public static Specification<Product> titleContainsCountryName(String countryName) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Join<Product, Company> productCompany = root.join("company");
                Join<Company, Country> country = productCompany.join("country");
                return criteriaBuilder.equal(country.get("name"), countryName);
            }
        };
    }

    public static Specification<Product> titleContainsCompanyName(String companyName) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Join<Product, Company> company = root.join("company");
                return criteriaBuilder.equal(company.get("name"), companyName);
            }
        };
    }


}
