package com.example.perfumeshop.service;

import com.example.perfumeshop.model.Perfume;
import com.example.perfumeshop.repository.PerfumeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PerfumeServiceTest {

    @Mock
    private PerfumeRepository perfumeRepository;

    @InjectMocks
    private PerfumeService perfumeService;

    @Test
    public void testFindAllPerfumes() {
        Perfume perfume = new Perfume(1L, "Dior Sauvage", "Dior", new BigDecimal("100.00"), "Fresh fragrance", 100, Perfume.Gender.MALE);
        when(perfumeRepository.findAll()).thenReturn(Arrays.asList(perfume));

        List<Perfume> perfumes = perfumeService.findAllPerfumes();
        assertEquals(1, perfumes.size());
        assertEquals("Dior Sauvage", perfumes.get(0).getName());
    }

    @Test
    public void testFindPerfumeById() {
        Perfume perfume = new Perfume(1L, "Dior Sauvage", "Dior", new BigDecimal("100.00"), "Fresh fragrance", 100, Perfume.Gender.MALE);
        when(perfumeRepository.findById(1L)).thenReturn(Optional.of(perfume));

        Perfume found = perfumeService.findPerfumeById(1L);
        assertEquals("Dior Sauvage", found.getName());
    }
}