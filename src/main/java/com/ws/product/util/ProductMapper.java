package com.ws.product.util;

import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final ProductDetailMapper detailMapper = new ProductDetailMapper();

    @Override
    public ProductDto toDto(Product entity) {
        if (entity == null) return null;

        List<ProductDetailDto> details = entity.getProductDetails() != null
                ? entity.getProductDetails().stream()
                .map(detailMapper::toDto)
                .toList()
                : List.of();

        return new ProductDto(
                entity.getProductId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getCategory(),
                details
        );
    }

    @Override
    public Product toEntity(ProductDto dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setProductId(dto.productId());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setCategory(dto.category());

        List<ProductDetail> details = dto.productDetails() != null
                ? dto.productDetails().stream()
                .map(detailDto -> {
                    ProductDetail detail = detailMapper.toEntity(detailDto);
                    detail.setProduct(product); // ⚠️ establecer la relación inversa
                    return detail;
                })
                .collect(Collectors.toList())
                : List.of();

        product.setProductDetails(details);

        return product;
    }
}
