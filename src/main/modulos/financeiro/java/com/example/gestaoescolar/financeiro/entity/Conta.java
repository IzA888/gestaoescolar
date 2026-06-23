package com.example.gestaoescolar.financeiro.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titularConta;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroConta;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteCredito;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConta status;

    public enum TipoConta {
        CHECKING, SAVINGS, INVESTMENT
    }

    public enum StatusConta {
        ACTIVE, INACTIVE, CLOSED, SUSPENDED
    }
}
