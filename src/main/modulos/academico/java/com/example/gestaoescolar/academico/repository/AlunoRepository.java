package com.example.gestaoescolar.academico.repository;

import com.example.gestaoescolar.academico.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByRegistrationNumber(String numeroMatricula);

    Optional<Aluno> findByEmail(String email);

    List<Aluno> findByStatus(Aluno.StatusAluno status);
}
