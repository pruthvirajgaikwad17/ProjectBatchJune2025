package com.example.ProductCatalogService_June2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String title;
    private String description;
    private String category;
    private String image;
    private Long id;
    private Double price;
}
