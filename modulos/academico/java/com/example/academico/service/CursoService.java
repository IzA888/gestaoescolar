package com.example.gestaoescolar.academico.service;

import com.example.gestaoescolar.academico.entity.Curso;
import com.example.gestaoescolar.academico.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository repo;

    public Curso create(Curso curso) {
        return repo.save(curso);
    }

    public Curso findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum curso com esse id: " + id));
    }

    public List<Curso> findAll() {
        return repo.findAll();
    }

    public Curso update(Long id, Curso curso) {
        Curso existente = findById(id);

        existente.setName(curso.getName());
        existente.setDescription(curso.getDescription());
        existente.setCredits(curso.getCredits());

        return repo.save(existente);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
