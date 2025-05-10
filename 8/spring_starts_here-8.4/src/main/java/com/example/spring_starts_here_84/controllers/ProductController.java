package com.example.spring_starts_here_84.controllers;

import com.example.spring_starts_here_84.models.Product;
import com.example.spring_starts_here_84.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }

    @PostMapping("/products")
    public String addProduct(
            Product newProduct,
            Model model) {
        productService.addProduct(newProduct);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }
}
