package com.example.simple_rest_app.controller;

import com.example.simple_rest_app.dto.ProductRequestDTO;
import com.example.simple_rest_app.dto.ProductResponseDTO;
import com.example.simple_rest_app.model.Product;
import com.example.simple_rest_app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(toResponseDTO(product));
    }

    @GetMapping
    public List<ProductResponseDTO> listAllProducts() {
        return productService.listAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        Product created = productService.create(dto);
        URI location = uriBuilder.path("/api/v1/products/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(toResponseDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable UUID id, @Valid @RequestBody ProductRequestDTO dto) {
        Product updateProduct = productService.update(id, dto);
        return ResponseEntity.ok(toResponseDTO(updateProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
