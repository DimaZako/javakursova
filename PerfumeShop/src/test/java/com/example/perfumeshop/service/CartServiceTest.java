package com.example.perfumeshop.service;

import com.example.perfumeshop.model.Cart;
import com.example.perfumeshop.model.CartItem;
import com.example.perfumeshop.model.Perfume;
import com.example.perfumeshop.model.User;
import com.example.perfumeshop.repository.CartItemRepository;
import com.example.perfumeshop.repository.CartRepository;
import com.example.perfumeshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void testAddItemToCart() {
        User user = new User(1L, "test@example.com", "password", User.Role.USER, "Test User");
        Cart cart = new Cart(1L, user);
        Perfume perfume = new Perfume(1L, "Dior Sauvage", "Dior", new BigDecimal("100.00"), "Fresh", 100, Perfume.Gender.MALE);
        CartItem cartItem = new CartItem(null, cart, perfume, 2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

        Cart result = cartService.addItemToCart(1L, cartItem);
        assertEquals(cart, result);
        verify(cartItemRepository).save(cartItem);
    }
}