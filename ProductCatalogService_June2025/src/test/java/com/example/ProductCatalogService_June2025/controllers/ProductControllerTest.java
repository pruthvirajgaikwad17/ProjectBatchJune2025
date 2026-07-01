package com.example.ProductCatalogService_June2025.controllers;

import com.example.ProductCatalogService_June2025.dtos.ProductDto;
import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private IProductService productService;

    @Test
    public void testGetProductById_valid_runSuccessfully() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone 17");
        when(productService.getProductId(1L)).thenReturn(product);

        // Act
        ResponseEntity<ProductDto> productDtoResponseEntity= productController.getProductById(1L);

        // Assert
        assertEquals("Iphone 17", productDtoResponseEntity.getBody().getName());
        assertEquals(productId, productDtoResponseEntity.getBody().getId());
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());

        verify(productService, times(1)).getProductId(productId);
    }

    @Test
    public void testGetProductByID_SadCase_Zero_Negative() {
        Exception e = assertThrows(IllegalArgumentException.class, ()-> productController.getProductById(-1L));
        assertEquals("Product Id cannot be negative", e.getMessage());

        Exception eZero = assertThrows(IllegalArgumentException.class, ()-> productController.getProductById(0L));
        assertEquals("Product Id cannot be zero", eZero.getMessage());

        verify(productService, times(0)).getProductId(0L);
        verify(productService, times(0)).getProductId(-1L);
    }
}