package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Transaction;
import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.WalletRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WalletDataLoader implements ApplicationRunner {
    private final WalletRepository walletRepository;

    public WalletDataLoader(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (walletRepository.count() == 0) {
            walletRepository.save(new Wallet("1", 500d, new ArrayList<>()));
            walletRepository.save(new Wallet("2", 3500d, createTransactions()));
            walletRepository.save(new Wallet("3", 93500d, new ArrayList<>()));
            walletRepository.save(new Wallet("4", 83500d, new ArrayList<>()));
            walletRepository.save(new Wallet("5", 123500d, new ArrayList<>()));
            walletRepository.save(new Wallet("6", 63533d, new ArrayList<>()));
            walletRepository.save(new Wallet("7", 41500d, new ArrayList<>()));
            walletRepository.save(new Wallet("8", 13500d, new ArrayList<>()));
        }
    }

    private List<Transaction> createTransactions()
    {
        return List.of(
                new Transaction("3", "seller@seller.com", "Mountain bike", 2500.00, LocalDate.of(2024, 6, 12)),
                new Transaction("2", "seller1", "T-Shirt", -55.00, LocalDate.of(2024, 6, 8)),
                new Transaction("3", "seller@seller.com", "Lawnmower", 1810.99, LocalDate.of(2024, 5, 24)),
                new Transaction("2", "seller2", "Electric grill", -760.00, LocalDate.of(2024, 5, 13)),
                new Transaction("2", "seller2", "Refrigerator", -3549.00, LocalDate.of(2024, 4, 1)),
                new Transaction("3", "seller@seller.com", "Office chair", 999.00, LocalDate.of(2024, 2, 4))
        );
    }
}
