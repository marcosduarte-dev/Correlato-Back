package com.marcospedroso.facens.correlato.dto.events;

import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IARequestEvent {
    private final AnaliseEquivalenciaData analiseEquivalenciaData;
}
