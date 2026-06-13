package com.example.ProductCatalogService_June2025.controllers;
import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        //Dummy Response for checking
        Product product = new Product();
        product.setId(1L);
        product.setPrice(1000D);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }
}
