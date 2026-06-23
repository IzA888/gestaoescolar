package com.example.gestaoescolar.financeiro.repository;

import com.example.gestaoescolar.financeiro.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumeroConta(String numeroConta);

    List<Conta> findByStatusConta(Conta.StatusConta status);

    List<Conta> findByTipoConta(Conta.TipoConta tipoConta);
}
