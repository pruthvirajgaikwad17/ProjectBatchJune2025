package com.example.ProductCatalogService_June2025.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tcp_ta")
public class Ta extends User{
    private Long numberOfRating;
}
