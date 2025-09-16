package com.ws.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.product.dto.ProductDetailDto;
import com.ws.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void givenValidProductId_whenGetDetailsByProductId_thenReturnProductDetails() throws Exception {
        // Arrange
        ProductDetailDto detail1 = new ProductDetailDto(1L, "Color", "Rojo", 1L);
        ProductDetailDto detail2 = new ProductDetailDto(2L, "Tamaño", "Mediano", 1L);

        List<ProductDetailDto> productDetails = Arrays.asList(detail1, detail2);
        given(productService.getDetailsByProductId(1L)).willReturn(productDetails);

        // Act & Assert
        mockMvc.perform(get("/products/{id}/details", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].attributeName").value("Color"))
                .andExpect(jsonPath("$[0].attributeValue").value("Rojo"))
                .andExpect(jsonPath("$[0].productId").value(1L))
                .andExpect(jsonPath("$[1].attributeName").value("Tamaño"))
                .andExpect(jsonPath("$[1].attributeValue").value("Mediano"))
                .andExpect(jsonPath("$[1].productId").value(1L));
    }
}
