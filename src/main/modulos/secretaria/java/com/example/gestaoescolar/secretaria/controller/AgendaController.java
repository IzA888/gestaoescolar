package com.example.gestaoescolar.secretaria.controller;

import com.example.gestaoescolar.secretaria.dto.AgendaDTO;
import com.example.gestaoescolar.secretaria.entity.Agenda;
import com.example.gestaoescolar.secretaria.service.AgendaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schedules")
public class AgendaController {

    @Autowired
    private AgendaService scheduleService;

    @PostMapping
    public ResponseEntity<AgendaDTO> create(@RequestBody AgendaDTO dto) {
        Agenda entity = toEntity(dto);
        Agenda saved = scheduleService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> findById(@PathVariable Long id) {
        return scheduleService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> findAll() {
        List<AgendaDTO> dtos = scheduleService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/employee/{funcionarioId}")
    public ResponseEntity<List<AgendaDTO>> findByEmployee(@PathVariable Long funcionarioId) {
        List<AgendaDTO> dtos = scheduleService.findByEmployee(funcionarioId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> update(@PathVariable Long id, @RequestBody AgendaDTO dto) {
        Agenda entity = toEntity(dto);
        Agenda updated = scheduleService.update(id, entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private Agenda toEntity(AgendaDTO dto) {
        if (dto == null) {
            return null;
        }
        Agenda entity = new Agenda();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private AgendaDTO toDto(Agenda entity) {
        if (entity == null) {
            return null;
        }
        AgendaDTO dto = new AgendaDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
