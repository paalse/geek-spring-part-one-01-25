package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productByTitle", query = "from Product p where p.title=:title"),
        @NamedQuery(name ="allProducts", query = "from Product")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // База сама будет генерировать идентификатор
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<LineItem> lineItems;

    public Product() {}

    public Product(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}