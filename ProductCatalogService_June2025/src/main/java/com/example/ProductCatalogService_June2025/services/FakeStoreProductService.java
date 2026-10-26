package com.example.ProductCatalogService_June2025.services;

import com.example.ProductCatalogService_June2025.clients.FakeStoreApiClient;
import com.example.ProductCatalogService_June2025.dtos.FakeStoreProductDto;
import com.example.ProductCatalogService_June2025.dtos.ProductDto;
import com.example.ProductCatalogService_June2025.models.Category;
import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Product getProductId(Long id) {
        String key = "product:" + id;

        // 1. Check cache
        Product cached = (Product) redisTemplate.opsForValue().get(key);
        if (cached != null) {
            System.out.println("CACHE HIT");
            return cached;
        }

        // 2. Miss, call FakeStore
        System.out.println("CACHE MISS");
        FakeStoreProductDto output = fakeStoreApiClient.getProductById(id);
        if (output == null) return null;

        Product product = from(output);

        // 3. Store, expire 10 min
        redisTemplate.opsForValue().set(key, product, Duration.ofMinutes(10));
        return product;
    }

    @Override
    public Product createProduct(Product inputProduct) {
        FakeStoreProductDto output = fakeStoreApiClient.createProduct(from(inputProduct));
        if(output == null) return null;
        return from(output);
    }

    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDto output = fakeStoreApiClient.replaceProduct(from(product), id);
        if(output == null) return null;
        return from(output);
    }


    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> output = fakeStoreApiClient.getAllProducts();

        if (output == null) {
            return null;
        }

        return output.stream()
                .map(this::from)
                .toList();
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
