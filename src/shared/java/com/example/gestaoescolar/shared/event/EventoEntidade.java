package com.example.gestaoescolar.shared.event;

import com.example.gestaoescolar.shared.dto.EntidadeBase;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Base event for inter-module communication.
 * Published by services via ApplicationEventPublisher.
 */
@Data
public class EventoEntidade extends ApplicationEvent {

    private EntidadeBase entity;
    private String sourceModule;

    public EventoEntidade(Object source, EntidadeBase entity, String sourceModule) {
        super(source);
        this.entity = entity;
        this.sourceModule = sourceModule;
    }
}
