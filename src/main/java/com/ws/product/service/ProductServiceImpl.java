package com.ws.product.service;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import com.ws.product.entity.Product;
import com.ws.product.exception.ProductAlreadyExistsException;
import com.ws.product.exception.ProductNotFoundException;
import com.ws.product.persistence.ProductDao;
import com.ws.product.util.DetailMapper;
import com.ws.product.util.ProductCache;
import com.ws.product.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDAO;
    @Autowired
    private ProductCache productCache;

    @Override
    public void saveProduct(ProductDto product) {
        if (productCache.isProductInCache(product)){
            throw new ProductAlreadyExistsException("El producto ya existe en el cache");
        }
        productDAO.save(ProductMapper.INSTANCE.productDTOToProduct(product));
    }

    @Override
    public void updateProduct(ProductDto product) {
        productDAO.update(ProductMapper.INSTANCE.productDTOToProduct(product));
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Optional<Product> product = productDAO.findById(productId);
        if (product.isPresent()) {
            productDAO.delete(productId);
        } else {
            throw new ProductNotFoundException("Producto no encontrado con id: " + productId);
        }
    }

    @Override
    public Optional<ProductDto> getProductById(Long productId) {
        Optional<Product> product = productDAO.findById(productId);
        return product.map(ProductMapper.INSTANCE::productToProductDTO);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productDAO.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDTO)
                .toList();
    }

    public List<ProductDetailDto> getDetailsByProductId(Long productId) {
        return productDAO.findAllDetailByProductId(productId)
                .stream()
                .map(DetailMapper.INSTANCE::detailToDetailDTO).toList();
    }
}
