package market;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productList;

    @PostConstruct
    public void initList () {
        productList = new ArrayList<Product>();
        productList.add(new Product(1, "bread", new BigDecimal(32.5)));
        productList.add(new Product(2, "cheese", new BigDecimal(150)));
        productList.add(new Product(3, "lemon", new BigDecimal(20.6)));
        productList.add(new Product(4, "tea", new BigDecimal(99)));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProduct (Integer id) {
        return searchProductID(id);
    }

    private Product searchProductID (Integer id) {
        for (Product p: productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
