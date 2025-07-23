package com.example.spring_starts_here_113.controller;

import com.example.spring_starts_here_113.client.ProductsClient;
import com.example.spring_starts_here_113.dto.ProductRequest;
import com.example.spring_starts_here_113.model.Product;
import com.example.spring_starts_here_113.service.ProductsService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public Mono<Product> createProduct(@RequestBody ProductRequest dto) {
        return productsService.createProduct(dto);
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productsService.listAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable UUID id) {
        return productsService.findById(id);
    }
}
