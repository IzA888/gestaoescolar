package com.example.gestaoescolar.academico.repository;

import com.example.gestaoescolar.academico.entity.Matricula;
import com.example.gestaoescolar.academico.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    List<Matricula> findByStudent(Aluno student);

    List<Matricula> findByStatus(Matricula.StatusMatricula status);
}
