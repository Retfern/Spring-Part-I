package market;

import market.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private UserService userService;

    @Autowired
    @Qualifier("newUserService")
    private void setUserService (UserService userService) {
        this.userService=userService;
    }

    @Autowired
    private void setProductRepository (ProductRepository productRepository) {
        this.productRepository=productRepository;
    }

    public List<Product> getListProducts () {
        return productRepository.getProductList();
    }

}
