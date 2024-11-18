package com.ws.product.util;

import com.ws.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductCache {

    private final ConcurrentHashMap<Integer, Product> productCache = new ConcurrentHashMap<>();

    public void addProductToCache(Product product) {
        productCache.put(product.hashCode(), product);
    }

    public boolean isProductInCache(Product product) {
        return productCache.containsKey(product.hashCode());
    }

    public void removeProductFromCache(Product product) {
        productCache.remove(product.hashCode());
    }
}
