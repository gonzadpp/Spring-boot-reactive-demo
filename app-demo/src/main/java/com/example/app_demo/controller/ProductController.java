package com.example.app_demo.controller;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.app_demo.models.Product;
import com.example.app_demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;


@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping({"/listar", "/"})
    public Mono<String> listProducts(Model model){
        Flux<Product> productsList = productRepository.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        });

        productsList.subscribe(product -> logger.info(product.getName()));

        model.addAttribute("products", productsList);
        model.addAttribute("title", "Electronic Goods List");
        return Mono.just("listProducts");

    }

    @GetMapping("/list-datadriver")
    public Mono<String> listProductDataDriver(Model model) {
        Flux<Product> productList = productRepository.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        }).delayElements(Duration.ofSeconds(1));

        model.addAttribute("products", new ReactiveDataDriverContextVariable(productList, 2));
        model.addAttribute("title", "Electronic Goods List");
        return Mono.just("listProducts");
    }
    
}
