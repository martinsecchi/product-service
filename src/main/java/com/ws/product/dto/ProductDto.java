package com.ws.product.dto;

import com.ws.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private List<ProductDetailDto> productDetails;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductDetailDto> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailDto> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto product = (ProductDto) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, category);
    }
}
