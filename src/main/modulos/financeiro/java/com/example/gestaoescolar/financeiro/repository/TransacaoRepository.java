package com.example.gestaoescolar.financeiro.repository;

import com.example.gestaoescolar.financeiro.entity.Conta;
import com.example.gestaoescolar.financeiro.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> buscarPorConta(Conta conta);

    List<Transacao> buscarPorStatus(Transacao.StatusTransacao status);

    List<Transacao> buscarPorDataTransacaoEntre(LocalDate dataInicio, LocalDate dataFim);

    List<Transacao> buscarPorContaEDataTransacaoEntre(Conta conta, LocalDate dataInicio, LocalDate dataFim);
}
