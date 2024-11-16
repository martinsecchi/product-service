package com.ws.product.service;

import com.ws.product.entity.Product;
import com.ws.product.persistence.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDAO;

    @Override
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productDAO.delete(productId);
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productDAO.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
}
