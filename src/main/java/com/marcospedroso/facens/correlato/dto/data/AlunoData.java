package com.marcospedroso.facens.correlato.dto.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoData {
	private String id;
    private String email;
    private String nome;
    private String matricula;
    private CursoData curso;
    private List<AnaliseEquivalenciaData> analisesEquivalencias;
}
