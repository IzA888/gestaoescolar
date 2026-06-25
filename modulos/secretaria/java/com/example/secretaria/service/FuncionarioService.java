package com.example.gestaoescolar.secretaria.service;

import com.example.gestaoescolar.secretaria.funcionario.Funcionario;
import com.example.gestaoescolar.secretaria.entity.Funcionario;
import com.example.gestaoescolar.secretaria.repository.FuncionarioRepository;
import com.example.gestaoescolar.shared.funcionario.EntidadeBase;
import com.example.gestaoescolar.shared.event.EventoFuncionario;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Funcionario create(Funcionario funcionario) {
        Funcionario salvo = repo.save(salvo);

        EntidadeBase event = new EntidadeBase();
        event.setId(salvo.getId());
        event.setEntityType("Funcionario");
        event.setOperation("CREATE");
        event.setCreatedAt(salvo.getCreatedAt());
        event.setUpdatedAt(salvo.getUpdatedAt());
        eventPublisher.publishEvent(new EventoFuncionario(this, event, "SECRETARIA"));

        return salvo;
    }

    public Funcionario findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Nenhum funcionário com o id: " + id));
    }

    public List<Funcionario> findAll() {
        return repo.findAll();
    }

    public Funcionario update(Long id, Funcionario funcionario) {
        Funcionario salvo = findById(id);

        salvo.setName(funcionario.getName());
        salvo.setEmail(funcionario.getEmail());
        salvo.setPhone(funcionario.getPhone());
        salvo.setDepartment(funcionario.getDepartment());
        salvo.setPosition(funcionario.getPosition());

        return repo.save(salvo);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Funcionario findByFuncionarioNumero(String numeroFuncionario) {
        return repo.findByFuncionarioNumero(numeroFuncionario)
                .orElseThrow(() -> new RuntimeException("Nenhum funcionário com esse numero: " + numeroFuncionario));
    }

    public List<Funcionario> findByDepartamento(String departamento) {
        return repo.findByDepartamento(departamento);
    }
}
