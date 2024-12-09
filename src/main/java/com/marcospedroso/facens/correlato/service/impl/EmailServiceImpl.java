package com.marcospedroso.facens.correlato.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.EmailCriacaoUsuarioDto;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    public void enviaEmailNovoUsuario(EmailCriacaoUsuarioDto dto) {
        try {
            //TODO: Link no botao do template do email!
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreplay@correlato.com");
            helper.setTo(dto.getDestinatario());
            helper.setSubject("Correlato - Novo Usuario");

            String template = carregaTemplateEmail();

            template = template.replace("{{nome}}", dto.getNome());
            template = template.replace("{{tipo_usuario}}", dto.getTipoUsuario());
            template = template.replace("{{email}}", dto.getDestinatario());
            template = template.replace("{{senha}}", dto.getSenha());

            helper.setText(template, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("Falha ao enviar email");
            e.printStackTrace();
        }
    }

    public String carregaTemplateEmail() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/email-CriacaoNovoUsuario.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
