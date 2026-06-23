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
@RequestMapping("/api/v1/employees")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

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

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(funcionarioService.create(toEntity(dto))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        return funcionarioService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        return ResponseEntity.ok(funcionarioService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(toDto(funcionarioService.update(id, toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee-number/{numeroFuncionario}")
    public ResponseEntity<FuncionarioDTO> findByEmployeeNumber(@PathVariable String numeroFuncionario) {
        return funcionarioService.findByEmployeeNumber(numeroFuncionario)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<FuncionarioDTO>> findByDepartment(@PathVariable String departamento) {
        return ResponseEntity.ok(funcionarioService.findByDepartment(departamento).stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }
}
