package com.example.gestaoescolar.secretaria.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO extends BaseDTO {

    private String nome;
    private String numeroFuncionario;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private String departamento;
    private String position;
    private String status;
}
