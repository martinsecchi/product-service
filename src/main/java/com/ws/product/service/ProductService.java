package com.ws.product.service;

import com.ws.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Long productId);

    Optional<Product> getProductById(Long productId);

    List<Product> getAllProducts();
}
