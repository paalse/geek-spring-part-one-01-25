package ru.paalse.persist;

import ru.paalse.service.ProductRepr;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productname;

    @Column(length = 512, nullable = true)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    public Product() {}


    public Product(ProductRepr product) {
        this.id = product.getId();
        this.productname = product.getProductname();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Product(String productname) {
        this.productname = productname;
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
