package com.example.ProductCatalogService_June2025.tableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tcp_mentor")
public class Mentor extends User{
    private Long numberOfHours;
}
