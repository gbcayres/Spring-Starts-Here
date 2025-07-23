package com.example.spring_starts_here_113.service;

import com.example.spring_starts_here_113.client.ProductsClient;
import com.example.spring_starts_here_113.dto.ProductRequest;
import com.example.spring_starts_here_113.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductsService {

    private final ProductsClient productsClient;

    public ProductsService(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    public Mono<Product> createProduct(ProductRequest productRequest) {
        String requestId = UUID.randomUUID().toString();

        return productsClient.post(requestId, productRequest);
    }

    public Flux<Product> listAllProducts() {
        return productsClient.getAll();
    }

   public Mono<Product> findById(UUID id) {
        return productsClient.get(id);
   }
}
