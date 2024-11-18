package com.ws.product.persistence;

import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import com.ws.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
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

    @Override
    public List<ProductDetail> findAllDetailByProductId(Long productId) {
        Optional<Product> product = productRepository.findByIdWithDetails(productId);
        return product.map(p -> p.getProductDetails() != null ? p.getProductDetails() : new ArrayList<ProductDetail>()).orElseGet(ArrayList::new);
    }


}
