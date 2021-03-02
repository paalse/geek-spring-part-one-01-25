package ru.geekbrains.persist;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> productnameLike(String name) {
        return (root, query, cb) -> cb.like(root.get("productname"), "%" + name + "%");
    }

    public static Specification<Product> priceFrom(BigDecimal priceFrom) {
        return (root, query, cb) -> cb.ge(root.get("price"), priceFrom);
    }

    public static Specification<Product> priceTo(BigDecimal priceTo) {
        return (root, query, cb) -> cb.le(root.get("price"), priceTo);
    }
}