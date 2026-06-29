package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Category;
import com.example.ProductCatalogService_June2025.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional // why we are adding this ?
    public void testLoading() {
        Optional<Category> categoryOptional = categoryRepo.findById(2L);
//        for(Product p: categoryOptional.get().getProducts()) {
//            System.out.println(p.getName());
//        }
    }
}