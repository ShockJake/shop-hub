package com.university.shophub.backend.models;

import java.time.LocalDate;

public record Transaction(String userId, String sellerName, String productName, Double amount, LocalDate date) {
}
