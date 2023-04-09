package ru.practicum.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    Long updateDistinctById(Long id);

    @Modifying
    @Query(value = "update  set discount = 0", nativeQuery = true)
    void clearDiscountColumn();

    @Query(value = "SELECT p FROM Product p ORDER BY RAND()")
    Product getRandomProduct();

    @Modifying
    @Query("UPDATE Product p SET p.discount = :discount WHERE p.id = :id")
    void updateDiscountById(@Param("id") Long id, @Param("discount") int discount);
}
