package com.marcospedroso.facens.correlato.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoAnaliseEquivalenciaData {
    private String id;
    private String email;
    private String nome;
    private String matricula;
    //private CursoData curso;
}
