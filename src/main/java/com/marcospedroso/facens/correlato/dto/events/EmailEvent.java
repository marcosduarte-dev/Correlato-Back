package com.marcospedroso.facens.correlato.dto.events;

import com.marcospedroso.facens.correlato.dto.EmailCriacaoUsuarioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailEvent {
    private final EmailCriacaoUsuarioDto emailDto;
}
