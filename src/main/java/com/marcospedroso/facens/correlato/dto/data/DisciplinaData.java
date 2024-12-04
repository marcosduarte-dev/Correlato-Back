package com.marcospedroso.facens.correlato.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaData {
	private Long id;
    private String codDisciplina;
    private String nome;
	private CursoData curso;
    private Float cargaHoraria;
    private String ementa;
    private String programa;
    private boolean ativo;
}
