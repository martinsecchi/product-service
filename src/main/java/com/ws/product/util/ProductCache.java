package com.ws.product.util;

import com.ws.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductCache {

    private final ConcurrentHashMap<Integer, Product> productCache = new ConcurrentHashMap<>();

    public synchronized boolean isProductInCache(Product product) {
        return productCache.computeIfAbsent(product.hashCode(), k -> product) != product;
    }

    public void removeProductFromCache(Product product) {
        productCache.remove(product.hashCode());
    }
}
