package com.example.spring_starts_here_121.controller;

import com.example.spring_starts_here_121.dto.PurchaseRequest;
import com.example.spring_starts_here_121.model.Purchase;
import com.example.spring_starts_here_121.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> listAllPurchases() {
        List<Purchase> purchases = purchaseService.findAll();
        return ResponseEntity.ok(purchases);
    }

    @PostMapping
    public void storePurchase(@RequestBody PurchaseRequest dto) {
        purchaseService.storePurchase(dto);
    }
}
