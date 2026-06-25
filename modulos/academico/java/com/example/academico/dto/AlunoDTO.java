package com.example.gestaoescolar.academico.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO extends BaseDTO {

    private String nome;
    private String numeroMatricula;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private String endereco;
    private String status;
}
