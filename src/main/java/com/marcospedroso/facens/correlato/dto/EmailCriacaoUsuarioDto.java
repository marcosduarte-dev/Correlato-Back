package com.marcospedroso.facens.correlato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailCriacaoUsuarioDto {
    private String destinatario;
    private String nome;
    private String senha;
    private String tipoUsuario;
}
