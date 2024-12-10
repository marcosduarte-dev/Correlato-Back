package com.marcospedroso.facens.correlato.listeners;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.marcospedroso.facens.correlato.dto.events.EmailEvent;
import com.marcospedroso.facens.correlato.service.impl.EmailServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailEventListener {
    private static final Logger logger = LoggerFactory.getLogger(EmailEventListener.class);
    private final EmailServiceImpl emailService;

    @Async
    @EventListener
    public void handleEmailEvent(EmailEvent event) throws InterruptedException {
        logger.info("Iniciando processamento do email em thread: {}", Thread.currentThread().getName());
        emailService.enviaEmailNovoUsuario(event.getEmailDto());
        logger.info("Email processado com sucesso");
    }
}