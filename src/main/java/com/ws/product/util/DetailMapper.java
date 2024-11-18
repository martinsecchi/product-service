package com.ws.product.util;
import com.ws.product.dto.ProductDetailDto;
import com.ws.product.dto.ProductDto;
import com.ws.product.entity.Product;
import com.ws.product.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetailMapper {
    DetailMapper INSTANCE = Mappers.getMapper(DetailMapper.class);

    @Mapping(source = "product.productId", target = "productId")
    ProductDetailDto detailToDetailDTO(ProductDetail productDetail);

    @Mapping(source = "productDetailId", target = "productDetailId")
    ProductDetail detailDTOToDetail(ProductDetailDto productDetailDto);
}
