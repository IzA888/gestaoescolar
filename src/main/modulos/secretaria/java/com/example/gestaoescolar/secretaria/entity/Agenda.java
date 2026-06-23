package com.example.gestaoescolar.secretaria.entity;

import com.example.gestaoescolar.shared.entity.EntidadeBaseJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Funcionario funcionario;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @Column(length = 100)
    private String local;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAgenda tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgenda status;

    public enum TipoAgenda {
        WORKING_SHIFT, MEETING, TRAINING, LEAVE
    }

    public enum StatusAgenda {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
