package com.ws.product.dto;

public record ProductDetailDto (
    Long productDetailId,
    String attributeName,
    String attributeValue,
    Long productId
){};
