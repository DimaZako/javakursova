package com.example.perfumeshop.repository;

import com.example.perfumeshop.model.Cart;
import com.example.perfumeshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);

    void deleteByCart(Cart cart);
}