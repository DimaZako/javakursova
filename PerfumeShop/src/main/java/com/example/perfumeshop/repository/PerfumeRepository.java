package com.example.perfumeshop.repository;

import com.example.perfumeshop.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
}