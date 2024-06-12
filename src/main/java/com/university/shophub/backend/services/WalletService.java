package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.models.Transaction;
import com.university.shophub.backend.models.User;
import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.UserRepository;
import com.university.shophub.backend.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Wallet findByUserId(String userId) {
        return walletRepository.findByUserId(userId);
    }

    public Wallet updateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Transactional
    public List<Transaction> manageTransaction(List<Product> toBuy, String userId) {
        List<Transaction> transactions = new ArrayList<>();
        Wallet wallet = walletRepository.findByUserId(userId);

        BigDecimal totalPrice = toBuy.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalPrice.doubleValue() > wallet.getBalance()) {
            return null;
        }

        toBuy.forEach(product -> {
            transactions.add(new Transaction(userId, product.getSellerName(), product.getPrice().doubleValue()));
            wallet.setBalance(wallet.getBalance() - product.getPrice().doubleValue());
            log.info("Managing product: {}", product);
            User seller = userRepository
                    .findByEmail(product.getSellerName())
                    .orElseThrow(() -> new NoSuchElementException("Seller not found"));
            Wallet sellerWallet = walletRepository.findByUserId(seller.getId());
            sellerWallet.setBalance(sellerWallet.getBalance() + product.getPrice().doubleValue());
            walletRepository.save(sellerWallet);
        });

        if (!transactions.isEmpty()) {
            List<Transaction> history = wallet.getHistory();
            history.addAll(transactions);
        }

        walletRepository.save(wallet);
        return transactions;
    }
}
