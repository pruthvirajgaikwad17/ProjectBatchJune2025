package com.example.ProductCatalogService_June2025.clients;

import com.example.ProductCatalogService_June2025.dtos.FakeStoreProductDto;
import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if (response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }
        return null;
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto inputFakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", inputFakeStoreProductDto ,FakeStoreProductDto.class);

        if(validateResponse(response)) {
            return response.getBody();
        }
        return null;
    }

    public FakeStoreProductDto replaceProduct(FakeStoreProductDto fakeStoreProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );

        if(validateResponse(response)) {
            return response.getBody();
        }
        return null;
    }

    public FakeStoreProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);

        if(validateResponse(response)) {
            return response.getBody();
        }
        return null;
    }


    private boolean validateResponse(ResponseEntity<FakeStoreProductDto> response) {
        return response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null;
    }
}
