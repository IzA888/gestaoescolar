package com.example.gestaoescolar.financeiro.service.impl;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;
import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.repository.ContaRepository;
import com.example.gestaoescolar.financeiro.service.ContaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository repository;

    public ContaServiceImpl(ContaRepository repository) {
        this.repository = repository;
    }

    private ContaDTO toDto(Conta e) {
        return new ContaDTO(e.getId(), e.getTitular(), e.getSaldo());
    }

    private Conta toEntity(ContaDTO dto) {
        return new Conta(dto.getId(), dto.getTitular(), dto.getSaldo());
    }

    @Override
    public ContaDTO create(ContaDTO dto) {
        Conta saved = repository.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public ContaDTO findById(Long id) {
        return repository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public List<ContaDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ContaDTO update(Long id, ContaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setTitular(dto.getTitular());
            existing.setSaldo(dto.getSaldo());
            return toDto(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
