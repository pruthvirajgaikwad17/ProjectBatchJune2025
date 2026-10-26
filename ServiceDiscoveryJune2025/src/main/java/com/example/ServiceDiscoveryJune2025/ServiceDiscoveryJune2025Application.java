package com.example.ServiceDiscoveryJune2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryJune2025Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryJune2025Application.class, args);
    }
}