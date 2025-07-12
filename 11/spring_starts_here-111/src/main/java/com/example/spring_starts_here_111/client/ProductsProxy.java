package com.example.spring_starts_here_111.client;

import com.example.spring_starts_here_111.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "products", url="${api.products.url}")
public interface ProductsProxy {
    @GetMapping("/{id}")
    Product getProductById(@PathVariable UUID id);

    @GetMapping
    List<Product> listAllProducts();

    @PostMapping
    void createProduct(@RequestBody Product product);
}
