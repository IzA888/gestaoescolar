package com.example.gestaoescolar.financeiro.controller;

import com.example.gestaoescolar.financeiro.dto.TransacaoDTO;
import com.example.gestaoescolar.financeiro.entity.Transacao;
import com.example.gestaoescolar.financeiro.service.TransacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransacaoController {

    @Autowired
    private TransacaoService transactionService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> create(@RequestBody TransacaoDTO dto) {
        Transacao entity = toEntity(dto);
        Transacao saved = transactionService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDTO> findById(@PathVariable Long id) {
        return transactionService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> findAll() {
        return ResponseEntity.ok(toDtoList(transactionService.findAll()));
    }

    @GetMapping("/account/{contaId}")
    public ResponseEntity<List<TransacaoDTO>> findByAccount(@PathVariable Long contaId) {
        return ResponseEntity.ok(toDtoList(transactionService.findByAccount(contaId)));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TransacaoDTO>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(toDtoList(transactionService.findByDateRange(startDate, endDate)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Transacao toEntity(TransacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        Transacao entity = new Transacao();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private TransacaoDTO toDto(Transacao entity) {
        if (entity == null) {
            return null;
        }
        TransacaoDTO dto = new TransacaoDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private List<TransacaoDTO> toDtoList(List<Transacao> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
