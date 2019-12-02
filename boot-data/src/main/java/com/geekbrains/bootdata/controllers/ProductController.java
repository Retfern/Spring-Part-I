package com.geekbrains.bootdata.controllers;
import com.geekbrains.bootdata.entities.FilterProduct;
import com.geekbrains.bootdata.entities.Product;
import com.geekbrains.bootdata.repositories.ProductSpecifications;
import com.geekbrains.bootdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/all")
    public String allListProduct(Model model) {
        List<Product> list = productService.findAllProduct();
        model.addAttribute("product", list);
        return "list";
    }

    @GetMapping("/filter")
    public String showSimpleForm(Model model) {
        List<Product> list = productService.findAllProduct();
        model.addAttribute("product", list);
        FilterProduct filterProduct = new FilterProduct();
        model.addAttribute("filterProduct", filterProduct);
        List<String> countryList = productService.countryName();
        model.addAttribute("countryList", countryList);
        List<String> companyList = productService.companyName();
        model.addAttribute("companyList", companyList);
        return "list_form";
    }

    @PostMapping("/process_form")
    public String getFilter(Model model, @ModelAttribute("filterProduct") FilterProduct filterProduct) {
        List<String> countryList = productService.countryName();
        model.addAttribute("countryList", countryList);
        List<String> companyList = productService.companyName();
        model.addAttribute("companyList", companyList);

        Page<Product> page = productService.findAllProduct(filterProduct, PageRequest.of(0, 20, Sort.Direction.ASC, "price"));
        model.addAttribute("product", page.getContent());
        model.addAttribute("productCount", page.getTotalElements());
        return "list_form";
    }

    @GetMapping("/param_filter")
    public String pagingExample(Model model,
                                @RequestParam(required = false, name = "min_price") Integer minPrice,
                                @RequestParam(required = false, name = "max_price") Integer maxPrice,
                                @RequestParam(required = false, name = "word") String word
    ) {
        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLesserThanOrEq(maxPrice));
        }
        if (word != null) {
            spec = spec.and(ProductSpecifications.titleContains(word));
        }
        Page<Product> page = productService.findAllProduct(spec, PageRequest.of(0, 5, Sort.Direction.ASC, "price"));
        model.addAttribute("product", page.getContent());
        model.addAttribute("productCount", page.getTotalElements());
        return "list";
    }
}
