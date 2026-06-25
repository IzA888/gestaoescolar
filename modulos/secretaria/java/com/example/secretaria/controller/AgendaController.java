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
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    @PostMapping
    public ResponseEntity<AgendaDTO> create(@RequestBody AgendaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            toDto(service.create(toEntity(dto)))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
            toDTO(service.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<AgendaDTO>> findAll() {
        return ReponseEntity.ok().body(toDTOList(service.findAll()));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<AgendaDTO>> findByEmployee(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok().body(
            toDTOList(service.findByFuncionario(funcionarioId))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> update(@PathVariable Long id, @RequestBody AgendaDTO dto) {
        return ResponseEntity.ok().body(
            toDto( service.update(id, toEntity(dto) ))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
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
    
    private List<AgendaDTO> toDto(List<Agenda> entity) {
        if (entity == null) {
            return null;
        }
        return entity.stream().map(Agenda::toDTO).collect(Collectors.toList());
    }
}
