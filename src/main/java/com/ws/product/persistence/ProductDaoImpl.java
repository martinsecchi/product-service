package com.ws.product.persistence;

import com.ws.product.entity.Product;
import com.ws.product.repository.ProductRepository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private ProductRepository productRepository;


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
                ifPresent(p -> productRepository.delete(p));
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findByIdWithDetails(productId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
