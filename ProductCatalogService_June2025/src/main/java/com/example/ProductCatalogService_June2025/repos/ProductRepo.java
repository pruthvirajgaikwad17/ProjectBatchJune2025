package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
