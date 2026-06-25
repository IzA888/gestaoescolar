package com.example.gestaoescolar.academico.repository;

import com.example.gestaoescolar.academico.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByCode(String code);

    List<Curso> findByStatus(Curso.StatusCurso status);
}
