package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.Purchase;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.repositories.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public record PurchaseService(PurchaseRepository purchaseRepository, UserService userService) {

    public Purchase getPurchaseById(String id) {
        return purchaseRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No purchase with such id: " + id));
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase deletePurchase(String id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No purchase with such id: " + id));
        purchaseRepository.deleteById(id);
        return purchase;
    }

    public List<Purchase> getPurchasesByUserId(String userId) {
        return purchaseRepository.getAllByUserId(userId);
    }

    public List<Purchase> getLastPurchasesByUserId(String userId) {
        return purchaseRepository.findTop4ByUserIdOrderByPurchaseDateDesc(userId);
    }

    public List<Purchase> savePurchases(List<Product> productsToBuy, User user) {
        final Map<String, List<Product>> productsGroupedBySeller = new HashMap<>();

        productsToBuy.forEach(product -> insertProductToMap(product, productsGroupedBySeller));
        final List<Purchase> purchases = productsGroupedBySeller.entrySet()
                .stream()
                .map(entry -> Purchase.builder()
                        .id(null)
                        .purchaseDate(LocalDate.now())
                        .sellerName(entry.getKey())
                        .sellerId(userService.getUserByUsername(entry.getKey()).getId())
                        .productNames(extractProductNames(entry.getValue()))
                        .userId(user.getId())
                        .build())
                .toList();

        return purchaseRepository.saveAll(purchases);
    }

    private void insertProductToMap(Product product, Map<String, List<Product>> map) {
        final User seller = userService.getUserByEmail(product.getSellerName());
        if (map.containsKey(seller.getName())) {
            log.debug("Inserting product: {}", product);
            map.get(seller.getName()).add(product);
        } else {
            log.debug("Adding product: {}", product);
            List<Product> products = new ArrayList<>();
            products.add(product);
            map.put(seller.getName(), products);
        }
    }

    private List<String> extractProductNames(List<Product> products) {
        return products.stream()
                .map(Product::getName)
                .toList();
    }
}
