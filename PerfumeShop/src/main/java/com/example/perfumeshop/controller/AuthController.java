package com.example.perfumeshop.controller;

import com.example.perfumeshop.config.JwtUtil;
import com.example.perfumeshop.model.User;
import com.example.perfumeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            // Перевірка, чи існує користувач із таким email
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.status(409).body("Error: User with email " + user.getEmail() + " already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during registration: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            User existingUser = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                String token = jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole().toString());
                return ResponseEntity.ok(token);
            }
            throw new RuntimeException("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during login: " + e.getMessage());
        }
    }
}