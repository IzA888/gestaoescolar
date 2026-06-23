package com.example.gestaoescolar.academico.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column
    private Double nota;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMatricula status;

    public enum StatusMatricula {
        ENROLLED, COMPLETED, WITHDRAWN, SUSPENDED
    }
}
