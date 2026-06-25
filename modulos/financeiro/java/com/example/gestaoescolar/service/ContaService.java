package com.example.gestaoescolar.financeiro.service.impl;

import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public Conta create(Conta conta) {
        return repository.save(conta);
    }

    public Conta findById(Long id) {
        return repository.findById(id).orElseThrow(new IllegalArgumentException("Nenhuma conta com esse id: " + id));
    }

    public List<Conta> findAll() {
        return repository.findAll();
    }

    public Conta update(Long id, Conta conta) {
        Conta existente = findById(id).map(existente -> {
            existente.setTitular(conta.getTitular());
            existente.setSaldo(conta.getSaldo());
        });
        return repository.save(existente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
