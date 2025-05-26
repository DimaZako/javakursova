package com.example.perfumeshop.controller;

import com.example.perfumeshop.model.Perfume;
import com.example.perfumeshop.repository.PerfumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PerfumeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Test
    public void testGetAllPerfumes() throws Exception {
        Perfume perfume = new Perfume(null, "Dior Sauvage", "Dior", new BigDecimal("100.00"), "Fresh", 100, Perfume.Gender.MALE);
        perfumeRepository.save(perfume);

        mockMvc.perform(get("/api/perfumes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dior Sauvage"));
    }
}