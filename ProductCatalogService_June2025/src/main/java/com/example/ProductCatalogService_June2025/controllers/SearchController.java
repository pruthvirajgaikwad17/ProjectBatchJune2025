package com.example.ProductCatalogService_June2025.controllers;

import com.example.ProductCatalogService_June2025.dtos.SearchRequestDto;
import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProduct(searchRequestDto.getQuery(), searchRequestDto.getPageSize(), searchRequestDto.getPageNumber(), searchRequestDto.getSortParams());
    }
}
