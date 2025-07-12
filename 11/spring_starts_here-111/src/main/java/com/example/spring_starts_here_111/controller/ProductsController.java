package com.example.spring_starts_here_111.controller;

import com.example.spring_starts_here_111.client.ProductsProxy;
import com.example.spring_starts_here_111.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsProxy productsProxy;

    public ProductsController(ProductsProxy productsProxy) {
        this.productsProxy = productsProxy;
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        System.out.println("Creating product: " + product);
        productsProxy.createProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productsProxy.listAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productsProxy.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
