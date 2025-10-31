package com.example.app_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.app_demo.models.Product;
import com.example.app_demo.repository.ProductRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class AppDemoApplication implements CommandLineRunner {

	private final ProductRepository productRepository;

	private static final Logger log = LoggerFactory.getLogger(AppDemoApplication.class);

	public AppDemoApplication(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AppDemoApplication.class, args);
	}

	@SuppressWarnings("null")
	@Override
	public void run(String... args) throws Exception {
		Flux.just(new Product("Laptop DELL", 100.00),
			new Product("Smartphone", 80.50),
			new Product("PlayStation 5", 50.22),
			new Product("SamrtTV Samsung", 64.22)
			)
			.flatMap(product -> productRepository.save(product))
			.subscribe(product -> log.info("insert: " + product.toString()));

	}
}
