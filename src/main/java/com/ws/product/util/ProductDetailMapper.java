package com.ws.product.util;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper implements Mapper<ProductDetail, ProductDetailDto> {

    @Override
    public ProductDetailDto toDto(ProductDetail entity) {
        if (entity == null) return null;

        return new ProductDetailDto(
                entity.getProductDetailId(),
                entity.getAttributeName(),
                entity.getAttributeValue(),
                entity.getProduct() != null ? entity.getProduct().getProductId() : null
        );
    }

    @Override
    public ProductDetail toEntity(ProductDetailDto dto) {
        if (dto == null) return null;

        ProductDetail detail = new ProductDetail();
        detail.setProductDetailId(dto.productDetailId());
        detail.setAttributeName(dto.attributeName());
        detail.setAttributeValue(dto.attributeValue());

        if (dto.productId() != null) {
            detail.setProduct(new Product(dto.productId()));
        }

        return detail;
    }
}
