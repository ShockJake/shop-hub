package com.university.shophub.frontend.api;

import com.university.shophub.backend.models.Wallet;
import com.university.shophub.backend.services.WalletService;
import com.university.shophub.frontend.util.AccessViolationChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/wallet")
public record WalletControllerAPI(WalletService walletService, AccessViolationChecker accessViolationChecker) {

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable @NonNull String id, Authentication authentication) {
        if (accessViolationChecker.isAccessViolated(authentication, false)) {
            throw new AccessDeniedException("Access denied");
        }
        log.info("User accessing wallet: {}", authentication.getName());
        return walletService.findByUserId(id);
    }

    @PatchMapping("/update")
    public Wallet updateWallet(@Validated @RequestBody Wallet wallet) {
        log.info("Patching wallet: {}", wallet);
        return walletService.updateWallet(wallet);
    }
}
