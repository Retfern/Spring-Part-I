package market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> listCart;
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository (ProductRepository productRepository) {
        this.productRepository=productRepository;
    }

    @PostConstruct
    public void cartInit () {
        listCart = new ArrayList<Product>();
    }

    public List<Product> getListCart() {
        return listCart;
    }

    public void addProduct (Integer id) {
        listCart.add(productRepository.getProduct(id));
    }

    public void deleteProduct (Integer id) {
        listCart.remove(productRepository.getProduct(id));
    }
}
