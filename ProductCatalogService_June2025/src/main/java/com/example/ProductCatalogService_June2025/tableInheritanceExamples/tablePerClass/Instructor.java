package com.example.ProductCatalogService_June2025.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tcp_instructor")
public class Instructor extends User {
    private String companyName;
}
