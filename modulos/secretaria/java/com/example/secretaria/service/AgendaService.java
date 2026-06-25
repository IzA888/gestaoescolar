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
        Funcionario f = funcionarioRepo.findById(agenda.getFuncionario().getId());
        agenda.setFuncionario(f);
        return repo.save(agenda);
    }

    public Agenda findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma agenda para o id: " + id));
    }

    public List<Agenda> findAll() {
        return repo.findAll();
    }

    public List<Agenda> findByFuncionario(Long funcionarioId) {
        Funcionario f = funcionarioRepo.findById(funcionarioId);
        return repo.findByFuncionario(f);
    }

    public Agenda update(Long id, Agenda agenda) {
        Agenda agenda = findById(id);

        agenda.setStartDateTime(agenda.getStartDateTime());
        agenda.setEndDateTime(agenda.getEndDateTime());
        agenda.setLocal(agenda.getLocal());
        agenda.setTipo(agenda.getTipo());
        agenda.setStatus(agenda.getStatus());
        if (agenda.getFuncionario() != null) {
            agenda.setFuncionario(agenda.getFuncionario());
        }

        return repo.save(agenda);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
