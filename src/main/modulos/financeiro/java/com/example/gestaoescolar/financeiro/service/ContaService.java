package com.example.gestaoescolar.financeiro.service;

import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.repository.ContaRepository;
import com.example.gestaoescolar.shared.dto.EntidadeBase;
import com.example.gestaoescolar.shared.event.EventoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContaService {

    @Autowired
    private ContaRepository repo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Conta create(Conta conta) {
        Conta salvo = repo.save(conta);

        EntidadeBase event = new EntidadeBase();
        event.setId(salvo.getId());
        event.setEntityType("Conta");
        event.setOperation("CREATE");
        event.setCreatedAt(salvo.getCreatedAt());
        event.setUpdatedAt(salvo.getUpdatedAt());
        eventPublisher.publishEvent(new EventoConta(this, event, "FINANCEIRO"));

        return salvo;
    }

    public Conta findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum registro de conta com o id: " + id));
    }

    public List<Conta> findAll() {
        return repo.findAll();
    }

    public Conta update(Long id, Conta conta) {
        Conta contaExistente = findById(id);

        contaExistente.setTitularConta(conta.getTitularConta());
        contaExistente.setSaldo(conta.getSaldo());
        contaExistente.setLimiteCredito(conta.getLimiteCredito());

        return repo.save(contaExistente);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Conta findByNumeroConta(String numeroConta) {
        return repo.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new RuntimeException("Nenhuma conta com esse numero: " + numeroConta));
    }
}
