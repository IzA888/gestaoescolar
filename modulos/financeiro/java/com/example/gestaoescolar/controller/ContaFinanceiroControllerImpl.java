package com.example.gestaoescolar.financeiro.controller;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.interfaces.ContaFinanceiroController;
import com.example.gestaoescolar.financeiro.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.example.gestaoescolar.financeiro.service.impl.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaFinanceiroControllerImpl implements ContaFinanceiroController {

    @Autowired
    private ContaService service;


    private Conta toEntity(ContaDTO dto) {
        Conta entity = new Conta();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private ContaDTO toDTO(Conta entity) {
        ContaDTO dto = new ContaDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
        
    private List<ContaDTO> toDTOList(List<Conta> entity) {        
        return entity.stream().map(Conta::toDTO).collect(Collectors.toList());
    }

    @Override
    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestBody ContaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(service.create(toEntity(dto))));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(service.findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        return ResponseEntity.ok().body(
                toDTOList(service.findAll())
                );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto) {
        return ResponseEntity.ok(toDTO(service.update(id, toEntity(dto))));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
