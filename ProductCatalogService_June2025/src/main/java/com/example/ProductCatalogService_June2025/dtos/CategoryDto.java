package com.example.ProductCatalogService_June2025.dtos;

import com.example.ProductCatalogService_June2025.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;

    private String name;

    private String description;

}
