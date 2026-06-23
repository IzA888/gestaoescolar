package com.example.gestaoescolar.academico.service;

import com.example.gestaoescolar.academico.entity.Aluno;
import com.example.gestaoescolar.academico.repository.AlunoRepository;
import com.example.gestaoescolar.shared.dto.EntidadeBase;
import com.example.gestaoescolar.shared.event.EventoAluno;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository repo;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Aluno create(Aluno student) {
        student.setStatus(Aluno.StatusAluno.valueOf(student.getStatus().toString()));

        Aluno saved = repo.save(student);

        // Publish event for inter-module communication
        EntidadeBase event = new EntidadeBase();
        event.setId(saved.getId());
        event.setEntityType("Aluno");
        event.setOperation("CREATE");
        event.setCreatedAt(saved.getCreatedAt());
        event.setUpdatedAt(saved.getUpdatedAt());
        eventPublisher.publishEvent(new EventoAluno(this, event, "ACADEMICO"));

        return saved;
    }

    public Aluno findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nenhum aluno com o id: " + id));
    }

    public List<Aluno> findAll() {
        return repo.findAll();
    }

    public Aluno update(Long id, Aluno student) {
        Aluno existing = findById(id);

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        existing.setAddress(student.getAddress());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Aluno findByRegistrationNumber(String numeroMatricula) {
        return repo.findByRegistrationNumber(numeroMatricula)
                .orElseThrow(() -> new IllegalArgumentException("Nenhum aluno com essa metrícula: " + numeroMatricula));
    }

}
