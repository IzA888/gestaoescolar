package com.example.gestaoescolar.academico.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO extends BaseDTO {

    private String nome;
    private String codigo;
    private String descricao;
    private Integer creditos;
    private String status;
}
