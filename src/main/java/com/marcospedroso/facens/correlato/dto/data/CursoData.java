package com.marcospedroso.facens.correlato.dto.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoData {
	private Long id;
    private String nome;
	private FaculdadeData faculdade;
	private boolean ativo;
}
