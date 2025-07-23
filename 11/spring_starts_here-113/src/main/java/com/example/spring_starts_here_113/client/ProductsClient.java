package com.example.spring_starts_here_113.client;

import com.example.spring_starts_here_113.dto.ProductRequest;
import com.example.spring_starts_here_113.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProductsClient {

    private final WebClient webclient;

    @Value("${api.products.base_url}")
    private String baseUrl;

    public ProductsClient(WebClient webclient) {
        this.webclient = webclient;
    }

    public Mono<Product> post(String requestId, ProductRequest productRequest) {
        return webclient.post()
                .uri(baseUrl)
                .header("requestId", requestId)
                .body(Mono.just(productRequest), ProductRequest.class)
                .retrieve()
                .bodyToMono(Product.class);
    }


    public Flux<Product> getAll() {
        return webclient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToFlux(Product.class);
    }


    public Mono<Product> get(UUID id) {
        String uri = baseUrl + "/" + id;

        return webclient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Product.class);
    }
}