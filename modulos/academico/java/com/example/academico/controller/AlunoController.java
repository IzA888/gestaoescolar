package com.example.gestaoescolar.academico.controller;

import com.example.gestaoescolar.academico.dto.AlunoDTO;
import com.example.gestaoescolar.academico.entity.Aluno;
import com.example.gestaoescolar.academico.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDto(alunoService.create(toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
        return alunoService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        return ResponseEntity.ok(alunoService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO dto) {
        return ResponseEntity.ok(toDto(alunoService.update(id, toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/registration/{numeroMatricula}")
    public ResponseEntity<AlunoDTO> findByRegistrationNumber(@PathVariable String numeroMatricula) {
        return alunoService.findByRegistrationNumber(numeroMatricula)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Aluno toEntity(AlunoDTO dto) {
        if (dto == null) {
            return null;
        }
        Aluno entity = new Aluno();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setNumeroMatricula(dto.getNumeroMatricula());
        return entity;
    }

    private AlunoDTO toDto(Aluno entity) {
        if (entity == null) {
            return null;
        }
        AlunoDTO dto = new AlunoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setNumeroMatricula(entity.getNumeroMatricula());
        return dto;
    }
}
