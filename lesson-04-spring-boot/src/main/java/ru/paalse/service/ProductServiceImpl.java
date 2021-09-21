package ru.paalse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paalse.persist.Product;
import ru.paalse.persist.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

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
    public Optional<ProductRepr> findById(long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr product) {
        productRepository.save(new Product(product));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
