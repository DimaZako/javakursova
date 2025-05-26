package com.example.perfumeshop.repository;

import com.example.perfumeshop.model.Cart;
import com.example.perfumeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}