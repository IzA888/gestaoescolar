package com.example.gestaoescolar.financeiro.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO extends BaseDTO {

    private Long contaId;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    private String tipo;
    private String descricao;
    private String status;
}
