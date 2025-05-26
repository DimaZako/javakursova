package com.example.perfumeshop.repository;

import com.example.perfumeshop.model.Order;
import com.example.perfumeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}