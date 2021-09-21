package ru.paalse.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.DoubleStream;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByProductnameLike(String productname);
}
