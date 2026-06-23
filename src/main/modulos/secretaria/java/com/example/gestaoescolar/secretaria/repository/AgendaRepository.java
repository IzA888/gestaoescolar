package com.example.gestaoescolar.secretaria.repository;

import com.example.gestaoescolar.secretaria.entity.Funcionario;
import com.example.gestaoescolar.secretaria.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByEmployee(Funcionario employee);

    List<Agenda> findByStatus(Agenda.StatusAgenda status);

    List<Agenda> findByStartDateTimeAndEndDateTime(LocalDateTime inicio, LocalDateTime fim);

    List<Agenda> findByScheduleType(Agenda.TipoAgenda scheduleType);
}
