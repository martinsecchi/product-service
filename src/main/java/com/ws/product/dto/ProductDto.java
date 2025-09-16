package com.ws.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        String category,
        List<ProductDetailDto> productDetails
) {}
