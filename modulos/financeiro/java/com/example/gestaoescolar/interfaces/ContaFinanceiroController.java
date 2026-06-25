package com.example.gestaoescolar.financeiro.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.gestaoescolar.financeiro.dto.ContaDTO;

public interface ContaFinanceiroController {

    ResponseEntity<ContaDTO> create(@RequestBody ContaDTO dto);

    ResponseEntity<ContaDTO> findById(@PathVariable Long id);

    ResponseEntity<List<ContaDTO>> findAll();

    ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto);

    ResponseEntity<Void> delete(@PathVariable Long id);
}
