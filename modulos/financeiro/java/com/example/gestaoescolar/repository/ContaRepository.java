package com.example.gestaoescolar.financeiro.repository;

import com.example.gestaoescolar.financeiro.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
