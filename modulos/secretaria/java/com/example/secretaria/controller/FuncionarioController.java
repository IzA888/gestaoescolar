package com.example.gestaoescolar.secretaria.controller;

import com.example.gestaoescolar.secretaria.dto.FuncionarioDTO;
import com.example.gestaoescolar.secretaria.model.Funcionario;
import com.example.gestaoescolar.secretaria.service.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    private Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Funcionario entity = new Funcionario();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    private FuncionarioDTO toDto(Funcionario entity) {
        if (entity == null) {
            return null;
        }
        FuncionarioDTO dto = new FuncionarioDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
        
    private List<FuncionarioDTO> toDtoList(List<Funcionario> entity) {
        if (entity == null) {
            return null;
        }
        return entity.stream().map(Funcionario::toDto).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(service.create(toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body.(
            toDto(service.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        return ResponseEntity.ok().body(
            toDtoList(service.findAll())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok().body(
            toDto(service.update(id, toEntity(dto)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{numeroFuncionario}")
    public ResponseEntity<FuncionarioDTO> findByNumeroFuncionario(@PathVariable String numeroFuncionario) {
        return ResponseEntity.ok().body(
            toDto(service.findByNumeroFuncionario(numeroFuncionario))
        );
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<FuncionarioDTO>> findByDepartment(@PathVariable String departamento) {
        return ResponseEntity.ok().body(
                toDtoList(service.findByDepartamento(departamento))
                );
    }
}
