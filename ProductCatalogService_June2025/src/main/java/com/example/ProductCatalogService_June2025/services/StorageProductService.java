package com.example.ProductCatalogService_June2025.services;

import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductId(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty()) {
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if(optionalProduct.isEmpty()) {
            return productRepo.save(product);
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        product.setId(id);
        return productRepo.save(product);
    }
}
