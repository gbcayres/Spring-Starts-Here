package com.example.simple_rest_app.service;

import com.example.simple_rest_app.dto.ProductRequestDTO;
import com.example.simple_rest_app.exception.ProductNotFoundException;
import com.example.simple_rest_app.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    Map<UUID, Product> products = new ConcurrentHashMap<UUID, Product>();

    public Product create(ProductRequestDTO dto) {
        Product product = new Product(UUID.randomUUID(),dto.name(), dto.price());

        products.put(product.getId(), product);

        return product;
    }

    public Product findById(UUID id) {
        return Optional.of(products.get(id))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public void delete(UUID id) {
        if (products.remove(id) == null) {
            throw new ProductNotFoundException(id);
        }
    }

    public Product update(UUID id, ProductRequestDTO dto) {
        Product product = findById(id);

        product.setName(dto.name());
        product.setPrice(dto.price());

        return product;
    }

    public List<Product> listAll() {
        return new ArrayList<>(products.values());
    }
}
