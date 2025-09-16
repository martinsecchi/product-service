package com.ws.product.service;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import com.ws.product.exception.ProductAlreadyExistsException;
import com.ws.product.exception.ProductNotFoundException;
import com.ws.product.persistence.ProductDao;
import com.ws.product.util.Mapper;
import com.ws.product.util.ProductCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDAO;
    private final Mapper<Product,ProductDto> productMapper;
    private final Mapper<ProductDetail,ProductDetailDto> detailMapper;

    private final ProductCache productCache;
    public static final String PRODUCT_NOT_FOUND = "Producto no encontrado con id: %d";
    public static final String PRODUCT_ALREADY_EXISTS = "Ya existe un producto con nombre: %s";

    @Override
    public void save(ProductDto product) {
        if (productCache.isProductInCache(product)) {
            throw new ProductAlreadyExistsException(String.format(PRODUCT_ALREADY_EXISTS, product.name()));
        }
        productDAO.save(productMapper.toEntity(product));
    }

    @Override
    public void update(Long id, ProductDto product) {
        Product prod = productDAO.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        if (product.productDetails() != null) {
            prod.setProductDetails(product.productDetails().stream().map(p ->
                    new ProductDetail(p.productDetailId(), p.attributeName(), p.attributeValue(), new Product(p.productId()))
            ).toList());
        }
        productDAO.update(prod);
    }

    @Override
    @Transactional
    public void delete(Long productId) {
        Optional<Product> product = productDAO.findById(productId);
        if (product.isPresent()) {
            productDAO.delete(productId);
        } else {
            throw new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, productId));
        }
    }

    @Override
    public Optional<ProductDto> getById(Long productId) {
        Optional<Product> product = productDAO.findById(productId);
        return product.map(productMapper::toDto);
    }

    @Override
    public List<ProductDto> getAll() {
        return productDAO.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    public List<ProductDetailDto> getDetailsByProductId(Long productId) {
        return productDAO.findAllDetailByProductId(productId)
                .stream()
                .map(detailMapper::toDto).toList();
    }

}
