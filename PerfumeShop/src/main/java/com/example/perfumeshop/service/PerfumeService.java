package com.example.perfumeshop.service;

import com.example.perfumeshop.model.Perfume;
import com.example.perfumeshop.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<Perfume> findAllPerfumes() {
        return perfumeRepository.findAll();
    }

    public Perfume findPerfumeById(Long id) {
        return perfumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfume not found"));
    }

    public Perfume savePerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }
}