package com.example.gestaoescolar.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
