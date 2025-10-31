package com.example.app_demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.app_demo.models.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
    
}
