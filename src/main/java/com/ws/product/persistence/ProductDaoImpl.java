package com.ws.product.persistence;

import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import com.ws.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public void delete(Long productId) {
        productRepository.findById(productId).
                ifPresent(productRepository::delete);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findByIdWithDetails(productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDetail> findAllDetailByProductId(Long productId) {
        return productRepository.findByIdWithDetails(productId)
                .map(Product::getProductDetails)
                .orElse(Collections.emptyList());
    }


}
