package com.example.gestaoescolar.secretaria.repository;

import com.example.gestaoescolar.secretaria.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmployeeNumber(String numeroFuncionario);

    Optional<Funcionario> findByEmail(String email);

    List<Funcionario> findByDepartment(String departamento);

    List<Funcionario> findByStatus(Funcionario.StatusFuncionario status);

    List<Funcionario> findByPosition(Funcionario.PosicaoFuncionario position);
}
