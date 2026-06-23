package com.example.gestaoescolar.academico.controller;

import com.example.gestaoescolar.academico.dto.CursoDTO;
import com.example.gestaoescolar.academico.entity.Curso;
import com.example.gestaoescolar.academico.service.CursoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> create(@RequestBody CursoDTO dto) {
        Curso created = cursoService.create(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll() {
        List<CursoDTO> dtos = cursoService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@PathVariable Long id, @RequestBody CursoDTO dto) {
        Curso updated = cursoService.update(id, toEntity(dto));
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Curso toEntity(CursoDTO dto) {
        if (dto == null) {
            return null;
        }
        Curso entity = new Curso();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private CursoDTO toDto(Curso entity) {
        if (entity == null) {
            return null;
        }
        CursoDTO dto = new CursoDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
