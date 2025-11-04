package com.example.app_demo.service;

import org.springframework.stereotype.Service;

import com.example.app_demo.models.Product;
import com.example.app_demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(String id){
        if (id == null) {
            return Mono.error(new NullPointerException("Product ID cannot be null"));
        }
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        if (product == null) {
            return Mono.error(new NullPointerException("Product object cannot be empty"));
        }
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        if (product == null) {
            return Mono.error(new NullPointerException("Product object cannot be empty"));
        }
        return productRepository.delete(product);
    }

    @Override
    public Flux<Product> findAllWithNameToUpperCase() {
        return productRepository.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        });
    }

}
