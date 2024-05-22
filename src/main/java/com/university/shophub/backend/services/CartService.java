package com.university.shophub.backend.services;

import com.university.shophub.backend.models.Cart;
import com.university.shophub.backend.models.Product;
import com.university.shophub.backend.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.nonNull;

@Service
public record CartService(CartRepository cartRepository, ProductService productService)
{
    public List<Product> getProducts(String userId)
    {
        return cartRepository.findByUserId(userId).getProductList();
    }

    public Cart addCart(String userId)
    {
        return cartRepository.save(Cart.builder()
                .userId(userId)
                .productList(emptyList())
                .build());
    }

    public void deleteCart(String userId)
    {
        cartRepository.delete(cartRepository.findByUserId(userId));
    }

    public void saveAll(List<Cart> carts)
    {
        cartRepository.saveAll(carts);
    }

    public void addProductToCart(String userId, long productId)
    {
        Cart cart = cartRepository.findByUserId(userId);
        if (nonNull(cart))
        {
            List<Product> productList = cart.getProductList();
            productList.add(productService.getProductById(productId));
            cart.setProductList(productList);

            cartRepository.save(cart);
        }
    }

    public void deleteProductFromCart(String userId, long productId)
    {
        Cart cart = cartRepository.findByUserId(userId);
        if (nonNull(cart))
        {
            List<Product> productList = cart.getProductList();
            productList.remove(productService.getProductById(productId));
            cart.setProductList(productList);

            cartRepository.save(cart);
        }
    }
}
