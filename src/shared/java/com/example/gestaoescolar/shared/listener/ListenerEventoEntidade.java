package com.example.gestaoescolar.shared.listener;

import com.example.gestaoescolar.shared.event.EventoEntidade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Base listener for entity events.
 * Provides logging and common handling for inter-module events.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ListenerEventoEntidade {

    @EventListener
    public void onEntityEvent(EventoEntidade event) {
        log.info("Entity Event Received - Module: {}, Entity: {}, Operation: {}, ID: {}",
                event.getSourceModule(),
                event.getEntity().getEntityType(),
                event.getEntity().getOperation(),
                event.getEntity().getId());
    }
}
