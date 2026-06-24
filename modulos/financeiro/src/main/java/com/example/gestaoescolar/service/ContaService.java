package com.example.gestaoescolar.financeiro.service;

import com.example.gestaoescolar.financeiro.dto.ContaDTO;

import java.util.List;

public interface ContaService {
    ContaDTO create(ContaDTO dto);

    ContaDTO findById(Long id);

    List<ContaDTO> findAll();

    ContaDTO update(Long id, ContaDTO dto);

    void delete(Long id);
}
