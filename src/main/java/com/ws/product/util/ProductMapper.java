package com.ws.product.util;
import com.ws.product.dto.ProductDto;
import com.ws.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productId", target = "productId")
    ProductDto productToProductDTO(Product product);

    @Mapping(source = "productId", target = "productId")
    Product productDTOToProduct(ProductDto productDTO);
}
