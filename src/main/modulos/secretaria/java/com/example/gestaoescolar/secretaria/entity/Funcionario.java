package com.example.gestaoescolar.secretaria.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroFuncionario;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String departamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PosicaoFuncionario position;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFuncionario status;

    public enum PosicaoFuncionario {
        SECRETARY, MANAGER, DIRECTOR, COORDINATOR, TEACHER
    }

    public enum StatusFuncionario {
        ACTIVE, INACTIVE, ON_LEAVE, RETIRED
    }
}
