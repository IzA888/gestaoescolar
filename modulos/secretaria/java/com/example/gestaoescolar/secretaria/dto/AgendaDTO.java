package com.example.gestaoescolar.secretaria.dto;

import com.example.gestaoescolar.shared.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO extends BaseDTO {

    private Long funcionarioId;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private String local;
    private String scheduleType;
    private String status;
}
