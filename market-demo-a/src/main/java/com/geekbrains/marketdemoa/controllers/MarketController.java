package com.geekbrains.marketdemoa.controllers;


import com.geekbrains.marketdemoa.entites.Product;
import com.geekbrains.marketdemoa.services.ProductService;

import com.geekbrains.marketdemoa.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;



@Controller
@RequestMapping("/products")
public class MarketController {
    private ProductService productService;

    @Autowired
    public MarketController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/filter")
    public String showSimpleForm(Model model) {
        model=productService.modelInit(model);
        ProductFilter productFilter = new ProductFilter();
        Page<Product> page = productService.findAll(productFilter.initSpecification(), productFilter.getPageRequest());
        model.addAttribute("product", page.getContent());
        model.addAttribute("productFilter", productFilter);
        model.addAttribute("page", page);
        return "list_form";
    }

    @PostMapping("/filter_param")
    public String getFilter(Model model, @ModelAttribute("productFilter") ProductFilter productFilter) {
        System.out.println("/filter_param start");
        model=productService.modelInit(model);
        Page<Product> page = productService.findAll(productFilter.initSpecification(), productFilter.getPageRequest());
        model.addAttribute("product", page.getContent());
        model.addAttribute("productCount", page.getTotalElements());
        model.addAttribute("page", page);
        return "list_form";
    }


}