package com.ws.product.persistence;

import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void save(Product product);

    void update(Product product);

    void update(List<Product> products);

    void delete(Long productId);

    Optional<Product> findById(Long productId);

    List<Product> findAll();

    List<ProductDetail> findAllDetailByProductId(Long productId);
}
