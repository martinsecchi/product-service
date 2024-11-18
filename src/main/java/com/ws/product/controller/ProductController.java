package com.ws.product.controller;


import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import com.ws.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        try {
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el producto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto product) {
        product.setProductId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<List<ProductDetailDto>> getDetailsByProductId(@PathVariable Long id) {
        List<ProductDetailDto> details = productService.getDetailsByProductId(id);
        return ResponseEntity.ok(details);
    }


}

