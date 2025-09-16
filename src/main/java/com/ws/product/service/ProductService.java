package com.ws.product.service;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void save(ProductDto product);

    public void update(Long id, ProductDto product);

    public void delete(Long productId);

    public Optional<ProductDto> getById(Long productId);

    public List<ProductDto> getAll();

    public List<ProductDetailDto> getDetailsByProductId(Long productId);

}
