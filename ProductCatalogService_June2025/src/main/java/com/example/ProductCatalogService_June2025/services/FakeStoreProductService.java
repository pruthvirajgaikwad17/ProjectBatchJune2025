package com.example.ProductCatalogService_June2025.services;

import com.example.ProductCatalogService_June2025.clients.FakeStoreApiClient;
import com.example.ProductCatalogService_June2025.dtos.FakeStoreProductDto;
import com.example.ProductCatalogService_June2025.dtos.ProductDto;
import com.example.ProductCatalogService_June2025.models.Category;
import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductId(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto != null && fakeStoreProductDtoResponseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public Product createProduct(Product inputProduct) {
        FakeStoreProductDto fakeStoreProductDtoInput = from(inputProduct);
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDtoInput,FakeStoreProductDto.class );
        FakeStoreProductDto fakeStoreProductDtoOutput = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDtoOutput != null && fakeStoreProductDtoResponseEntity.getStatusCode() == HttpStatusCode.valueOf(201)) {
            return from(fakeStoreProductDtoOutput);
        }
        return null;
    }

    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDto output = fakeStoreApiClient.replaceProduct(from(product), id);
        if(output == null) return null;
        return from(output);
    }


    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());

        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
