package com.geekbrains.marketdemoa.utils;

import com.geekbrains.marketdemoa.entites.Item;
import com.geekbrains.marketdemoa.repositories.specifications.ItemSpecifications;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ItemFilter {
    private Specification<Item> spec;
    private StringBuilder filterDefinition;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortField = "id";

    public ItemFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ItemSpecifications.priceGEThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ItemSpecifications.priceLEThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("cat_id") && !map.get("cat_id").isEmpty()) {
            Long catId = Long.parseLong(map.get("cat_id"));
            spec = spec.and(ItemSpecifications.categoryIdEquals(catId));
            filterDefinition.append("&cat_id=").append(catId);
        }

        if (map.containsKey("sorting") && !map.get("sorting").isEmpty()) {
            filterDefinition.append("&sorting=").append(map.get("sorting"));
            initPageRequest(map.get("sorting"));
        }
//        if (map.containsKey("company") && !map.get("company").isEmpty()) {
//            System.out.println("checkbox is true" + map.get("company"));
//        }

    }

    private void initPageRequest(String sortingType) {
        String[] subStr = splitString(sortingType);
        sortField = subStr[0];
        String direction = subStr[1];
        if(direction.equals("ASC")) {
            sortDirection = Sort.Direction.ASC;
        }else {
            sortDirection = Sort.Direction.DESC;;
        }
    }

    private String[] splitString(String sortingType) {
        String[] subStr;
        String delimeter = "/";
        subStr = sortingType.split(delimeter);
        return subStr;
    }
}
