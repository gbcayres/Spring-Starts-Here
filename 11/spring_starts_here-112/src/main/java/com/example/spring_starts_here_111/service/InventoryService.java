package com.example.spring_starts_here_111.service;

import com.example.spring_starts_here_111.client.ProductsClient;
import com.example.spring_starts_here_111.dto.ProductDTO;
import com.example.spring_starts_here_111.model.Product;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ProductsClient productsClient;

    public InventoryService(ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    public Product createProduct(ProductDTO dto) {
        return productsClient.createProduct(dto);
    }
}
