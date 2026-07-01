package com.example.ProductCatalogService_June2025.controllers;


import com.example.ProductCatalogService_June2025.dtos.ProductDto;
import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    IProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts_runSuccessfully() throws Exception {
        // we have created a response which will return on api call
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone15");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        when(productService.getAllProducts()).thenReturn(productList); // when get all products is called from the product service then return mocked product list

        // this is the response we will get from the controller so we have hardcoded for testing
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Iphone15");
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);

        String response = objectMapper.writeValueAsString(productDtos);
        System.out.println(response);

        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(response));
    }

    @Test
    public void testCreateProduct_runSuccessfully() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone15");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Iphone15");

        String productDtoString = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/products").content(productDtoString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(productDtoString));
    }
}
