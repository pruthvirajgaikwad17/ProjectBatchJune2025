package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // Save or update a product
    @Override
    Product save(Product entity);

    // Find one product by its id
    @Override
    Optional<Product> findById(Long aLong);

    // Get every product in the table
    @Override
    List<Product> findAll();

    // Get every product, sorted by given sort
    @Override
    List<Product> findAll(Sort sort);

    // Get products page by page
    @Override
    Page<Product> findAll(Pageable pageable);

    // Get products whose ids are in the list
    @Override
    List<Product> findAllById(Iterable<Long> ids);

    // Save many products at once
    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    // Check if product with id exists
    @Override
    boolean existsById(Long id);

    // Total number of products
    @Override
    long count();

    // Delete a product by id
    @Override
    void deleteById(Long id);

    // Delete given product entity
    @Override
    void delete(Product entity);

    // Delete many products by ids
    @Override
    void deleteAllById(Iterable<? extends Long> ids);

    // Delete many product entities
    @Override
    void deleteAll(Iterable<? extends Product> entities);

    // Delete every product
    @Override
    void deleteAll();

    // Find products with price between min and max
    List<Product> findByPriceBetween(Double min, Double max);

    // Find all products, ordered by price ascending
    List<Product> findAllByOrderByPrice();

    // Find products whose name contains text (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find all products under a category id
    List<Product> findByCategory_Id(Long categoryId);

    // Find products with price <= given value
    List<Product> findByPriceLessThanEqual(Double price);

    // Find products with price >= given value
    List<Product> findByPriceGreaterThanEqual(Double price);

    // Find a product by exact name
    Optional<Product> findByName(String name);

    // Check if a product with this name exists
    boolean existsByName(String name);

    // Count how many products are in a category
    long countByCategory_Id(Long categoryId);

    // Return only the name of product with given id
    @Query("SELECT p.name FROM Product p WHERE p.id = :id")
    String getNameOfProductById(@Param("id") Long id);

    // Get products of a category, cheapest first
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.price ASC")
    List<Product> findProductsByCategorySortedByPrice(@Param("categoryId") Long categoryId);

    // Get average price of products in a category
    @Query("SELECT AVG(p.price) FROM Product p WHERE p.category.id = :categoryId")
    Double getAveragePriceByCategory(@Param("categoryId") Long categoryId);

    // Update only the price of a product by id
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
    int updatePriceById(@Param("id") Long id, @Param("price") Double price);

    // Delete every product in a category
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.category.id = :categoryId")
    int deleteByCategoryId(@Param("categoryId") Long categoryId);
}