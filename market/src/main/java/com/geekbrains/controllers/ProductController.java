package com.geekbrains.controllers;

import com.geekbrains.entities.Product;
import com.geekbrains.entities.Student;
import com.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

// http://localhost:8189/app/products/...
@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8189/app/products/show
    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public String showAllProducts(Model model) {

        List<Product> products = productService.getListProducts();
        model.addAttribute("products", products);

        return "all_products";
    }

    // http://localhost:8189/app/products/info/1
    @RequestMapping(path = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductByID(id).orElseThrow(() -> new RuntimeException());
    }

    // GET http://localhost:8189/app/products/show_form
    @GetMapping("/show_form")
    public String showSimpleForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        List<String> companyList = Arrays.asList(new String[]{"chinaTea", "frenchBread", "russiaCheese", "non"});
        model.addAttribute("companyList", companyList);
        return "product_form";
    }

    // POST http://localhost:8189/app/products/process_form
    @PostMapping("/process_form")
    public String processForm(@ModelAttribute("product") Product product) {
        productService.addNewProductForm(product);
        return "product_form_result";
    }

    // GET http://localhost:8189/app/products/edit_form_product
    @PostMapping("/edit_form_product/{id}")
    public String showEditProductForm(Model model, @PathVariable Long id) {
        Product product = productService.getProductByID(id).orElseThrow(() -> new RuntimeException());
        model.addAttribute("product", product);
        List<String> companyList = Arrays.asList(new String[]{"chinaTea", "frenchBread", "russiaCheese", "non"});
        model.addAttribute("companyList", companyList);
        return "product_form";
    }
}
