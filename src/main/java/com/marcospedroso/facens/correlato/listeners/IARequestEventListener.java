package com.marcospedroso.facens.correlato.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.marcospedroso.facens.correlato.dto.events.IARequestEvent;
import com.marcospedroso.facens.correlato.service.impl.IARequestServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IARequestEventListener {
    private static final Logger logger = LoggerFactory.getLogger(IARequestEventListener.class);
    private final IARequestServiceImpl service;

    @Async
    @EventListener
    public void handleIARequestEvent(IARequestEvent event) throws InterruptedException {
        logger.info("Iniciando processamento do request para IA na thread: {}", Thread.currentThread().getName());
        service.analisarEquivalenciaPorIA(event.getAnaliseEquivalenciaData());
        logger.info("Request IA processado com sucesso");
    }

}
