package com.geekbrains.marketdemoa.utils;
import com.geekbrains.marketdemoa.entites.Product;
import com.geekbrains.marketdemoa.repositories.specifications.ProductSpecifications;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;


@Data
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> countries;
    private List<String> companies;
    private String category;
    private String sorting;

    public ProductFilter() {
        this.filterDefinition = new StringBuilder();
    }

    public Specification<Product> initSpecification () {
        this.spec = Specification.where(null);
            if (minPrice != null) {
                spec = spec.and(ProductSpecifications.priceGreaterThanOrEq(this.getMinPrice()));
            }
            if (maxPrice != null) {
                spec = spec.and(ProductSpecifications.priceLesserThanOrEq(this.getMaxPrice()));
            }

            Specification<Product> specCompany = Specification.where(null);
            if (companies!=null && !companies.isEmpty()) {
                for (String c : companies) {
                    specCompany = specCompany.or(ProductSpecifications.titleContainsCompanyName(c));
                }
                spec = Specification.where(spec).and(specCompany);
            }

            Specification<Product> specCountry = Specification.where(null);
            if (countries!=null && !countries.isEmpty()) {
                for (String c : countries) {
                    specCountry = specCountry.or(ProductSpecifications.titleContainsCountryName(c));
                }
                spec = Specification.where(spec).and(specCountry);
            }

            if (category!=null && !category.isEmpty()) {
                spec = spec.and(ProductSpecifications.titleContainsCategoryName(category));
            }
        return spec;
    }

    public PageRequest getPageRequest () {
        int page = 5;
        if (sorting!=null) {
            if (sorting.equals("PriceToMax")) {
                return PageRequest.of(0, page, Sort.Direction.ASC, "price");
            }
            if (sorting.equals("PriceToMin")) {
                return PageRequest.of(0, page, Sort.Direction.DESC, "price");
            }
            if (sorting.equals("Non") || sorting.equals("IdToMax")) {
                return PageRequest.of(0, page, Sort.Direction.ASC, "id");
            }
            if (sorting.equals("IdToMin")) {
                return PageRequest.of(0, page, Sort.Direction.DESC, "id");
            }
            if (sorting.equals("CompanyASC")) {
                return PageRequest.of(0, page, Sort.Direction.ASC, "company");
            }
            if (sorting.equals("CompanyDESC")) {
                return PageRequest.of(0, page, Sort.Direction.DESC, "company");
            }
            if (sorting.equals("TitleASC")) {
                return PageRequest.of(0, page, Sort.Direction.ASC, "title");
            }
            if (sorting.equals("TitleDESC")) {
                return PageRequest.of(0, page, Sort.Direction.DESC, "title");
            }
        }
        return PageRequest.of(0, page, Sort.Direction.ASC, "id");
    }
}
