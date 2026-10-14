package com.example.ProductCatalogService_June2025.services;

import com.example.ProductCatalogService_June2025.dtos.SortParam;
import com.example.ProductCatalogService_June2025.dtos.SortType;
import com.example.ProductCatalogService_June2025.models.Product;
import com.example.ProductCatalogService_June2025.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;

    public Page<Product> searchProduct(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {
//        Sort sortById = Sort.by("id").descending();
//        Sort overallSort = Sort.by("price").descending().and(sortById);

        Sort sort = null;
        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = sort.by(sortParams.get(0).getSortCriteria());
            } else {
                sort = sort.by(sortParams.get(0).getSortCriteria()).descending();
            }

            for(int i = 1; i < sortParams.size(); i++) {
                if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()));
                } else {
                    sort = sort.and(sort.by(sortParams.get(i).getSortCriteria()).descending());
                }
            }
        }
        return productRepo.findByNameEquals(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}
