package com.example.ProductCatalogService_June2025.dtos;

import com.example.ProductCatalogService_June2025.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Category category;
}
