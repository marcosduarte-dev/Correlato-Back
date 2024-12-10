package com.marcospedroso.facens.correlato.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IAData {
    private boolean aprovado;
    private String diferencas;
    private String equivalencias;
    private float porcentagemEquivalencia;
}
