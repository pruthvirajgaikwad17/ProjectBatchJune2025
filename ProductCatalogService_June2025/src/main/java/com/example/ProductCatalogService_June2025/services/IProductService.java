package com.example.ProductCatalogService_June2025.services;

import com.example.ProductCatalogService_June2025.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductId(Long id);

    public Product createProduct(Product product);

    List<Product> getAllProducts();

    public Product replaceProduct(Product product, Long id);
}
