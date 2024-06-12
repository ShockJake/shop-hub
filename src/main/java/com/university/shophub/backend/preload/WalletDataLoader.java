package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.repositories.WalletRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class WalletDataLoader implements ApplicationRunner {
    private final WalletRepository walletRepository;

    public WalletDataLoader(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (walletRepository.count() == 0) {
            walletRepository.save(new Wallet("1", 3500d, new ArrayList<>()));
            walletRepository.save(new Wallet("2", 3500d, new ArrayList<>()));
            walletRepository.save(new Wallet("3", 3500d, new ArrayList<>()));
        }
    }
}
