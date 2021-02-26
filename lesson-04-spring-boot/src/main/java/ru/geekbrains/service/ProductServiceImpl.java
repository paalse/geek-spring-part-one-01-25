package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findWithFilter(String productnameFilter) {
        return productRepository.findProductByProductnameLike(productnameFilter).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }



    @Override
    public List<ProductRepr> findWithFilter(String productnameFilter, BigDecimal priceFromFilter, BigDecimal priceToFilter) {

        return productRepository.findProduct(productnameFilter, priceFromFilter, priceToFilter).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ProductRepr> findById(long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr product) {
        Product productToSave = new Product(product);
        productRepository.save(productToSave);
        if (product.getId() == null) {
            product.setId(productToSave.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
