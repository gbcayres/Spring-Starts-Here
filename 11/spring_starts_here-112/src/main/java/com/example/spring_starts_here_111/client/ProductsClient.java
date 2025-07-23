package com.example.spring_starts_here_111.client;

import com.example.spring_starts_here_111.dto.ProductDTO;
import com.example.spring_starts_here_111.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class ProductsClient {
    private final RestTemplate rest;

    @Value("${api.products.url}")
    private String productsServiceUrl;

    public ProductsClient(RestTemplate rest) {
        this.rest = rest;
    }

    public Product createProduct(ProductDTO dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", UUID.randomUUID().toString());

        HttpEntity<ProductDTO> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<Product> response = rest.exchange(productsServiceUrl, HttpMethod.POST, entity, Product.class);

        return response.getBody();
        }
}
