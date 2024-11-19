package com.ws.product.util;

import com.ws.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductCache {

    private final ConcurrentHashMap<Integer, ProductDto> productCache = new ConcurrentHashMap<>();

    public synchronized boolean isProductInCache(ProductDto product) {
        return productCache.computeIfAbsent(product.hashCode(), k -> product) != product;
    }

    public void removeProductFromCache(ProductDto product) {
        productCache.remove(product.hashCode());
    }
}
