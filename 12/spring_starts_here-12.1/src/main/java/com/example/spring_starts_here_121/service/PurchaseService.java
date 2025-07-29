package com.example.spring_starts_here_121.service;

import com.example.spring_starts_here_121.dto.PurchaseRequest;
import com.example.spring_starts_here_121.model.Purchase;
import com.example.spring_starts_here_121.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAllPurchases();
    }

    public void storePurchase(PurchaseRequest dto) {
        Purchase newPurchase = new Purchase(dto.product(), dto.price());
        purchaseRepository.storePurchase(newPurchase);
    }
}
