package com.example.gestaoescolar.shared.event;

import com.example.gestaoescolar.shared.dto.EntidadeBase;

/**
 * Conta-specific event for financial module.
 */
public class EventoConta extends EventoEntidade {

    public EventoConta(Object source, EntidadeBase entity, String sourceModule) {
        super(source, entity, sourceModule);
    }
}
