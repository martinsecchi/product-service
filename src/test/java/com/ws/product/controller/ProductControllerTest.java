package com.ws.product.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.product.dto.ProductDetailDto;
import com.ws.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenValidProductId_whenGetDetailsByProductId_thenReturnProductDetails() throws Exception {
        ProductDetailDto detail1 = new ProductDetailDto();
        detail1.setProductDetailId(1L);
        detail1.setAttributeName("Color");
        detail1.setAttributeValue("Rojo");
        detail1.setProductId(1L);

        ProductDetailDto detail2 = new ProductDetailDto();
        detail2.setProductDetailId(2L);
        detail2.setAttributeName("Tamaño");
        detail2.setAttributeValue("Mediano");
        detail2.setProductId(1L);

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
