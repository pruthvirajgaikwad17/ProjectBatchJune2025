package com.example.ProductCatalogService_June2025.repos;

import com.example.ProductCatalogService_June2025.models.Category;
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
public interface CategoryRepo extends JpaRepository<Category, Long> {

    // Find one category by its id
    @Override
    Optional<Category> findById(Long id);

    // Save or update a category
    @Override
    Category save(Category entity);

    // Save many categories at once
    @Override
    <S extends Category> List<S> saveAll(Iterable<S> entities);

    // Get every category in the table
    @Override
    List<Category> findAll();

    // Get every category, sorted by given sort
    @Override
    List<Category> findAll(Sort sort);

    // Get categories page by page
    @Override
    Page<Category> findAll(Pageable pageable);

    // Get categories whose ids are in the list
    @Override
    List<Category> findAllById(Iterable<Long> ids);

    // Check if category with id exists
    @Override
    boolean existsById(Long id);

    // Total number of categories
    @Override
    long count();

    // Delete a category by id
    @Override
    void deleteById(Long id);

    // Delete given category entity
    @Override
    void delete(Category entity);

    // Delete many categories by ids
    @Override
    void deleteAllById(Iterable<? extends Long> ids);

    // Delete many category entities
    @Override
    void deleteAll(Iterable<? extends Category> entities);

    // Delete every category
    @Override
    void deleteAll();

    // Find a category by exact name
    Optional<Category> findByName(String name);

    // Find categories whose name contains text (case insensitive)
    List<Category> findByNameContainingIgnoreCase(String name);

    // Check if a category with this name exists
    boolean existsByName(String name);

    // Get all categories sorted by name A to Z
    List<Category> findAllByOrderByNameAsc();

    // Return only the name of category with given id
    @Query("SELECT c.name FROM Category c WHERE c.id = :id")
    String getNameOfCategoryById(@Param("id") Long id);

    // Find the category that owns a given product
    @Query("SELECT c FROM Category c JOIN c.products p WHERE p.id = :productId")
    Optional<Category> findByProductId(@Param("productId") Long productId);

    // Count how many products belong to a category
    @Query("SELECT COUNT(p) FROM Category c JOIN c.products p WHERE c.id = :id")
    Long countProductsInCategory(@Param("id") Long id);

    // Update only the name of a category by id
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.name = :name WHERE c.id = :id")
    int updateNameById(@Param("id") Long id, @Param("name") String name);
}