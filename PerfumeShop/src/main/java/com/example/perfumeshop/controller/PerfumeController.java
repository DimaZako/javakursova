package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Perfume;
import com.example.perfumeshop.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping
    public ResponseEntity<List<Perfume>> getAllPerfumes() {
        return ResponseEntity.ok(perfumeService.findAllPerfumes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> getPerfumeById(@PathVariable Long id) {
        return ResponseEntity.ok(perfumeService.findPerfumeById(id));
    }

    @PostMapping
    public ResponseEntity<Perfume> createPerfume(@RequestBody Perfume perfume) {
        return ResponseEntity.ok(perfumeService.savePerfume(perfume));
    }
}