package com.example.spring_starts_here_111.controller;

import com.example.spring_starts_here_111.client.ProductsClient;
import com.example.spring_starts_here_111.dto.ProductDTO;
import com.example.spring_starts_here_111.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsClient productsProxy;

    public ProductsController(ProductsClient productsProxy) {
        this.productsProxy = productsProxy;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO dto) {
        Product created = productsProxy.createProduct(dto);
        System.out.println("Created product successfully: " + created);
        return ResponseEntity.ok(created);
    }
}
