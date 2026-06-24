package com.example.gestaoescolar.secretaria.service;

import com.example.gestaoescolar.secretaria.entity.Funcionario;
import com.example.gestaoescolar.secretaria.entity.Agenda;
import com.example.gestaoescolar.secretaria.repository.FuncionarioRepository;
import com.example.gestaoescolar.secretaria.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository repo;

    @Autowired
    private FuncionarioRepository funcionarioRepo;

    public Agenda create(Agenda agenda) {
        Funcionario employee = funcionarioRepo.findById(agenda.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Funcionario not found"));

        agenda.setEmployee(employee);
        return repo.save(agenda);
    }

    public Agenda findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma agenda para o id: " + id));
    }

    public List<Agenda> findAll() {
        return repo.findAll();
    }

    public List<Agenda> findByEmployee(Long funcionarioId) {
        Funcionario employee = funcionarioRepo.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionario not found"));
        return repo.findByEmployee(employee);
    }

    public Agenda update(Long id, Agenda agenda) {
        Agenda schedule = findById(id);

        schedule.setStartDateTime(agenda.getStartDateTime());
        schedule.setEndDateTime(agenda.getEndDateTime());
        schedule.setLocation(agenda.getLocation());
        schedule.setScheduleType(agenda.getScheduleType());
        schedule.setStatus(agenda.getStatus());
        if (agenda.getEmployee() != null) {
            schedule.setEmployee(agenda.getEmployee());
        }

        return repo.save(schedule);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
