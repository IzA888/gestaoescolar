package com.example.gestaoescolar.shared.event;

import com.example.gestaoescolar.shared.dto.EntidadeBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Aluno-specific event for academic module.
 */
@Data
public class EventoAluno extends EventoEntidade {

    public EventoAluno(Object source, EntidadeBase entity, String sourceModule) {
        super(source, entity, sourceModule);
    }
}
