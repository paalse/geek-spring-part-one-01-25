package ru.geekbrains.persist;

import javax.persistence.*;

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
    private int price;

    public Product() {}

    public Product(String title, int price) {
        this.title = title;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}