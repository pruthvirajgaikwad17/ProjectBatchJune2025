package com.example.ProductCatalogService_June2025.tableInheritanceExamples.joinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String companyName;
}
