package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;

import javax.persistence.*;
import java.math.BigDecimal;

// Класс взаимодействия с БД
@Entity
@Table(name ="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String productname;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    public Product() {
    }

    public Product(String productname, String description, BigDecimal price) {
        this.productname = productname;
        this.description = description;
        this.price = price;
    }

    public Product(ProductRepr product) {
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

    public void setProductname(String name) {
        this.productname = name;
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