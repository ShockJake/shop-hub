package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Cart;
import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.repositories.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public List<Product> getProducts(String userId) {
        return cartRepository.findByUserId(userId).getProductList();
    }

    public BigDecimal getTotalPrice(String userId) {
        List<Product> products = cartRepository.findByUserId(userId).getProductList();
        return products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Cart addCart(String userId) {
        return cartRepository.save(Cart.builder()
                .userId(userId)
                .productList(emptyList())
                .build());
    }

    public void deleteCart(String userId) {
        cartRepository.delete(cartRepository.findByUserId(userId));
    }

    public void saveAll(List<Cart> carts) {
        cartRepository.saveAll(carts);
    }

    public void addProductToCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (nonNull(cart)) {
            List<Product> productList = cart.getProductList();
            productList.add(productService.getProductById(productId));
            cart.setProductList(productList);

            cartRepository.save(cart);
        }
    }

    public void deleteProductFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (nonNull(cart)) {
            List<Product> productList = cart.getProductList();
            Product productById = productService.getProductById(productId);
            productList.removeIf(product -> product.getId().equals(productById.getId()));
            cart.setProductList(productList);
            cartRepository.save(cart);
        }
    }
}
