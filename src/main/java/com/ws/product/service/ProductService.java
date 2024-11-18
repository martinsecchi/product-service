package com.ws.product.service;

import com.ws.product.dto.ProductDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    void saveProduct(ProductDto product);

    void updateProduct(ProductDto product);

    void deleteProduct(Long productId);

    Optional<ProductDto> getProductById(Long productId);

    List<ProductDto> getAllProducts();
}
