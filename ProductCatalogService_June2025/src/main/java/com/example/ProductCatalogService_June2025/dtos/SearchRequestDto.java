
package com.example.ProductCatalogService_June2025.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private Integer pageNumber;
    private Integer pageSize;
    List<SortParam> sortParams = new ArrayList<>();
}
