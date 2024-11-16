package com.ws.product.repository;

import com.ws.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query("SELECT p FROM Product p")
    @Override
    public List<Product> findAll();

}
