package com.university.shophub.backend.preload;

import com.university.shophub.backend.models.Cart;
import com.university.shophub.backend.services.CartService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyList;

@Component
public record CartDataLoader(CartService cartService) implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        List<Cart> initialCarts = List.of(new Cart("1", emptyList()),
                new Cart("2", emptyList()),
                new Cart("3", emptyList()),
                new Cart("4", emptyList()),
                new Cart("5", emptyList()),
                new Cart("6", emptyList()),
                new Cart("7", emptyList()),
                new Cart("8", emptyList())
        );

        cartService.saveAll(initialCarts);
    }
}
