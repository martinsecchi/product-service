package com.ws.product.repository;

import com.ws.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    @Override
    public List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.productId = :productId")
    Optional<Product> findByIdWithDetails(Long productId);


}
