package com.example.gestaoescolar.financeiro.interfaces;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/financeiro/accounts")
public interface ContaFinanceiroController {

    @PostMapping
    ResponseEntity<ContaDTO> create(@RequestBody ContaDTO dto);

    @GetMapping("/{id}")
    ResponseEntity<ContaDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<ContaDTO>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
