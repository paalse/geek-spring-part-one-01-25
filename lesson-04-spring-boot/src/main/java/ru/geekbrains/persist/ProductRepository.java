package ru.geekbrains.persist;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByProductnameLike(String productname);

    @Query("select p from Product p " +
            "where (p.productname like :productname or :productname is null)" +
            "and (p.price >= :pricefrom or :pricefrom is null)" +
            "and (p.price <= :priceto or :priceto is null)")
    List<Product> findProduct(@Param("productname") String productname, @Param("pricefrom")BigDecimal pricefrom, @Param("priceto") BigDecimal priceto);
}