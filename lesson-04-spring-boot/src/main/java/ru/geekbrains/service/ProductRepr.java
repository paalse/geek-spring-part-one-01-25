package ru.geekbrains.service;

import ru.geekbrains.persist.Product;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

//DTO, класс взаимодействия с пользователем
public class ProductRepr {

    private Long id;

    @NotEmpty
    private String productname;

    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal price;

    public ProductRepr() {}

    public ProductRepr(String productname, String description, BigDecimal price) {
        this.productname = productname;
        this.description = description;
        this.price = price;
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.productname = product.getProductname();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
