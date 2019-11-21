package market;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;

    public Product(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id +
                  ", name='" + name + '\'' +
                  ", price=" + price +
                  '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }
}
