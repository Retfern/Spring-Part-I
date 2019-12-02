package com.geekbrains.bootdata.entities;

import java.util.List;

public class FilterProduct {

    private Integer minPrice;

    private Integer maxPrice;

    private List<String> countries;

    private List<String> companies;

    public FilterProduct(Integer minPrice, Integer maxPrice, List<String> countries, List<String> companies) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.countries = countries;
        this.companies = companies;
    }

    public FilterProduct() {
    }

    @Override
    public String toString() {
        return "FilterProduct{" +
                  "minPrice=" + minPrice +
                  ", maxPrice=" + maxPrice +
                  ", countries=" + countries +
                  ", companies=" + companies +
                  '}';
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getCompanies() {
        return companies;
    }
}
