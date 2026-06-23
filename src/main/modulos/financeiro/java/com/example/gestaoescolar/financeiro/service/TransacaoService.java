package com.example.gestaoescolar.financeiro.service;

import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.entity.Transacao;
import com.example.gestaoescolar.financeiro.repository.ContaRepository;
import com.example.gestaoescolar.financeiro.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransacaoService {

    @Autowired
    private TransacaoRepository repo;

    @Autowired
    private ContaRepository contaRepo;

    public Transacao create(Transacao transaction) {
        Conta conta = contaRepo.findById(transaction.getConta().getId());

        transaction.setConta(conta);
        return repo.save(transaction);
    }

    public Transacao findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma transação para o id: " + id));
    }

    public List<Transacao> findAll() {
        return repo.findAll();
    }

    public List<Transacao> findByConta(Long contaId) {
        Conta conta = contaRepo.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta not found"));
        return repo.findByConta(conta);
    }

    public List<Transacao> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return repo.findByTransactionDateBetween(startDate, endDate);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
