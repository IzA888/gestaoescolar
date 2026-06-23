package com.example.gestaoescolar.financeiro.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Conta conta;

    @Column(nullable = false)
    private LocalDate dataTransacao;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    @Column(length = 200)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransacao status;

    public enum TipoTransacao {
        DEPOSIT, WITHDRAWAL, TRANSFER, PAYMENT, INTEREST
    }

    public enum StatusTransacao {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
}
