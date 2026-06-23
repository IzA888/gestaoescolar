package com.example.gestaoescolar.academico.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Integer creditos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCurso status;

    public enum StatusCurso {
        ACTIVE, INACTIVE, ARCHIVED
    }
}
