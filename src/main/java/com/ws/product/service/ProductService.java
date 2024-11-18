package com.ws.product.service;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public void saveProduct(ProductDto product);

    public void updateProduct(ProductDto product);

    public void deleteProduct(Long productId);

    public Optional<ProductDto> getProductById(Long productId);

    public List<ProductDto> getAllProducts();

    public List<ProductDetailDto> getDetailsByProductId(Long productId);

}
