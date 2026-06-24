package com.example.gestaoescolar.shared.event;

import com.example.gestaoescolar.shared.dto.EntidadeBase;

/**
 * Funcionario-specific event for secretariat module.
 */
public class EventoFuncionario extends EventoEntidade {

    public EventoFuncionario(Object source, EntidadeBase entity, String sourceModule) {
        super(source, entity, sourceModule);
    }
}
