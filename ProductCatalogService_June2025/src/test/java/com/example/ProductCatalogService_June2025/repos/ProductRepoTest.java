package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void testQueries() {
//        List<Product> productList = productRepo.findByPriceBetween(100.0, 1000.0);
//        List<Product> productOrderList = productRepo.findAllByOrderByPrice();
//        for(Product product: productOrderList) {
//            System.out.println(product.getPrice());
//        }

        System.out.println(productRepo.getNameOfProductById(2L));
    }
}