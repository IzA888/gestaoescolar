package com.example.gestaoescolar.financeiro.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO extends BaseDTO {

    private String titularConta;
    private String numeroConta;
    private BigDecimal saldo;
    private BigDecimal limiteCredito;
    private String accountType;
    private String status;
}
