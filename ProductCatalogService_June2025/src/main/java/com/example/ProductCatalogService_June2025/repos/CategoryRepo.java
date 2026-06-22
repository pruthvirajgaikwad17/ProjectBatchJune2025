package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
