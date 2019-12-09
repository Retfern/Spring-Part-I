package com.geekbrains.marketdemoa.services;

import com.geekbrains.marketdemoa.entites.Basket;
import com.geekbrains.marketdemoa.entites.Company;
import com.geekbrains.marketdemoa.entites.Country;
import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.repositories.CompanyRepository;
import com.geekbrains.marketdemoa.repositories.CountryRepository;
import com.geekbrains.marketdemoa.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private CompanyRepository companyRepository;
    private CountryRepository countryRepository;
    private Basket basket;

    @Autowired
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Page<Item> findAll(Specification<Item> spec, Pageable pageable) {
        return itemRepository.findAll(spec, pageable);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Country> findAllCountry () {
        return countryRepository.findAll();
    }

    public List<Company> findAllCompany () {
        return companyRepository.findAll();
    }

    public Basket getBasket() {
        return basket;
    }



    public Model modelInit (Model model) {
        model.addAttribute("countryList", countryName());
        model.addAttribute("companyList", companyName());
        model.addAttribute("sortingList", getSortingList());
        return model;
    }

    private List<String> getSortingList() {
        List<String> sortingList = new ArrayList<>();
        sortingList.add("price");
        sortingList.add("id");
        sortingList.add("title");
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
}
