package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findProductByProductnameLike(String productname);

    @Query("select p from Product p " +
            "where (p.productname like :productname or :productname is null)" +
            "and (p.price >= :priceFrom or :priceFrom is null)" +
            "and (p.price <= :priceTo or :priceTo is null)")
    List<Product> findWithFilter(@Param("productname") String productnameFilter,
                                 @Param("priceFrom")BigDecimal priceFrom,
                                 @Param("priceTo") BigDecimal priceTo);
}