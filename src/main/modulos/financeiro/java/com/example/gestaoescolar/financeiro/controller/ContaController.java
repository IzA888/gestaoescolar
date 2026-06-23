package com.example.gestaoescolar.financeiro.controller;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.service.ContaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class ContaController implements ContaFinanceiroController {

    @Autowired
    private ContaService contaService;

    private ContaDTO toDto(Object entity) {
        ContaDTO dto = new ContaDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private <T> T toEntity(ContaDTO dto, Class<T> entityClass) {
        T entity = BeanUtils.instantiateClass(entityClass);
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestBody ContaDTO dto) {
        Conta entity = toEntity(dto, Conta.class);
        Conta created = contaService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Long id) {
        return contaService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        return ResponseEntity.ok(contaService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto) {
        Conta entity = toEntity(dto, Conta.class);
        Conta updated = contaService.update(id, entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account-number/{numeroConta}")
    public ResponseEntity<ContaDTO> findByAccountNumber(@PathVariable String numeroConta) {
        return contaService.findByAccountNumber(numeroConta)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
