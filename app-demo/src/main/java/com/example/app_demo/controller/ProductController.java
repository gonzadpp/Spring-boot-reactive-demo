package com.example.app_demo.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.app_demo.models.Product;
import com.example.app_demo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ProductController {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping({"/listar", "/"})
    public Mono<String> listProducts(Model model){
        Flux<Product> productsList = productService.findAll();
        productsList.subscribe(product -> logger.info(product.getName()));

        model.addAttribute("products", productsList);
        model.addAttribute("title", "Electronic Goods List");
        return Mono.just("listProducts");

    }

    @GetMapping("/list-datadriver")
    public Mono<String> listProductDataDriver(Model model) {
        Flux<Product> productList = productService.findAll()
            .delayElements(Duration.ofSeconds(1));

        model.addAttribute("products", new ReactiveDataDriverContextVariable(productList, 2));
        model.addAttribute("title", "Electronic Goods List");
        return Mono.just("listProducts");
    }

    @GetMapping("/list-full")
    public Mono<String> listProductWithNameToUpperCase(Model model) {
        Flux<Product> productList = productService.findAllWithNameToUpperCase()
            .delayElements(Duration.ofSeconds(1));

        model.addAttribute("products", new ReactiveDataDriverContextVariable(productList, 1));
        model.addAttribute("Title", "Electronic Goods List With Name to Upper Case");
        return Mono.just("listProducts");
    }
    
    
}
