package com.marcospedroso.facens.correlato.dto.data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IAResponseData {
    private Long id;
    private AnaliseEquivalenciaData analiseEquivalencia;
    private String equivalencias;
    private String diferencas;
    private Boolean aprovado;
    private Float porcentagemEquivalencia;
    private LocalDateTime createdAt;
}
