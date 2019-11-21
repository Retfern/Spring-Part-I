package market;

import market.config.MarketConfig;
import market.userservice.NewUserService;
import market.userservice.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MarketApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MarketConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        System.out.println(productService.getListProducts());

        NewUserService userService = context.getBean("newUserService", NewUserService.class);

        Cart cart = userService.getCart();
        System.out.println(cart.getListCart());
        cart.addProduct(1);
        System.out.println(cart.getListCart());
        cart.deleteProduct(1);
        System.out.println(cart.getListCart());

        SimpleBean simpleBean = context.getBean("simpleBean", SimpleBean.class);
        simpleBean.printSimpleBean();
        context.close();
    }
}
