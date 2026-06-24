package com.example.gestaoescolar.financeiro.controller;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import com.example.gestaoescolar.financeiro.interfaces.ContaFinanceiroController;
import com.example.gestaoescolar.financeiro.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ContaFinanceiroControllerImpl implements ContaFinanceiroController {

    private final ContaService service;

    public ContaFinanceiroControllerImpl(ContaService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ContaDTO> create(ContaDTO dto) {
        ContaDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/v1/financeiro/accounts/" + created.getId())).body(created);
    }

    @Override
    public ResponseEntity<ContaDTO> findById(Long id) {
        ContaDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ContaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<ContaDTO> update(Long id, ContaDTO dto) {
        ContaDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
