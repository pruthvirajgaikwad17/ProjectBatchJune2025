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

@Component
public class FakeStoreApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

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

    private Boolean validateResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity) {
        if (fakeStoreProductDtoResponseEntity.getBody() != null && fakeStoreProductDtoResponseEntity.getStatusCode() == HttpStatusCode.valueOf(200)) {
            return true;
        }
        return false;
        }
}
