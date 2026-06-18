package com.example.ProductCatalogService_June2025.controllers;
import com.example.ProductCatalogService_June2025.dtos.CategoryDto;
import com.example.ProductCatalogService_June2025.dtos.ProductDto;
import com.example.ProductCatalogService_June2025.models.Category;
import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
            if(productId <= 0) {
                throw new IllegalArgumentException("Product Id not found");
            }
            Product product = productService.getProductId(productId);
            if (product == null) {
                return null;
            }
            ProductDto productDto = from(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product output = productService.createProduct(product);
        if(output == null){
            return null;
        }
        return from(output);
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product updated = productService.replaceProduct(product, id);
        if (updated == null) return null;
        return from(updated);
    }

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());

        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());

        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
