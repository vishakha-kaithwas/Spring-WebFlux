package com.example.demo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    // Get all products
    public Flux<Product> getAllProducts() {
        return Flux.fromIterable(products);
    }

    // Get product by ID
    public Mono<Product> getProductById(String id) {
        return Flux.fromIterable(products)
                   .filter(p -> p.getId().equals(id))
                   .next();
    }

    // Create product
    public Mono<Product> createProduct(Product product) {
        products.add(product);
        return Mono.just(product);
    }

    // Delete product
    public Mono<Void> deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
        return Mono.empty();
    }
}
