package com.geekbrains.marketdemoa.services;

import com.geekbrains.marketdemoa.entites.Category;
import com.geekbrains.marketdemoa.entites.Company;
import com.geekbrains.marketdemoa.entites.Country;
import com.geekbrains.marketdemoa.entites.Product;
import com.geekbrains.marketdemoa.repositories.CategoryRepository;
import com.geekbrains.marketdemoa.repositories.CompanyRepository;
import com.geekbrains.marketdemoa.repositories.CountryRepository;
import com.geekbrains.marketdemoa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable);
    }

    public List<Country> findAllCountry () {
        return countryRepository.findAll();
    }

    public List<Company> findAllCompany () {
        return companyRepository.findAll();
    }

    public List<Category> findAllCategory () {return categoryRepository.findAll();}

    public Model modelInit (Model model) {
        model.addAttribute("categoryList", categoryName());
        model.addAttribute("countryList", countryName());
        model.addAttribute("companyList", companyName());
        model.addAttribute("sortingList", getSortingList());
        return model;
    }

    private List<String> getSortingList() {
        List<String> sortingList = new ArrayList<>();
        sortingList.add("Non");
        sortingList.add("PriceToMax");
        sortingList.add("PriceToMin");
        sortingList.add("IdToMax");
        sortingList.add("IdToMin");
        sortingList.add("TitleASC");
        sortingList.add("TitleDESC");
        sortingList.add("CompanyASC");
        sortingList.add("CompanyDESC");
        return sortingList;
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

    public List<String> categoryName () {
        List<String> category = new ArrayList<>();
        for (Category c: findAllCategory()) {
            category.add(c.getName());
        }
        return category;
    }


}
