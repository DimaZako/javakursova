package com.example.perfumeshop.service;

import com.example.perfumeshop.model.Cart;
import com.example.perfumeshop.model.CartItem;
import com.example.perfumeshop.model.User;
import com.example.perfumeshop.repository.CartItemRepository;
import com.example.perfumeshop.repository.CartRepository;
import com.example.perfumeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addItemToCart(Long userId, CartItem cartItem) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
        cartItem.setCart(cart);
        cartItemRepository.save(cartItem);
        return cart;
    }

    public Cart removeItemFromCart(Long userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cartItemRepository.deleteById(itemId);
        return cart;
    }
}