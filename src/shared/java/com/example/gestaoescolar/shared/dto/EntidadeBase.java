package com.example.gestaoescolar.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Base DTO for inter-module communication via ApplicationEventPublisher.
 * Used to transfer entity data between modules as events.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeBase {

    private Long id;
    private String entityType;
    private String operation; // CREATE, UPDATE, DELETE
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
