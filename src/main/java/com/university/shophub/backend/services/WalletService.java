package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.UserRepository;
import com.university.shophub.backend.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public record WalletService(WalletRepository walletRepository, UserRepository userRepository) {

    public Wallet findByUserId(String userId) {
        return walletRepository.findByUserId(userId);
    }

    public Wallet updateWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }
}
