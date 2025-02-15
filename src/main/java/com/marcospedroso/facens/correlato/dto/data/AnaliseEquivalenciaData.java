package com.marcospedroso.facens.correlato.dto.data;

import java.util.List;

import com.marcospedroso.facens.correlato.enums.StatusAnaliseEquivalencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseEquivalenciaData {
	private Long id;
	private UsuarioData professorResponsavel;
	private DisciplinaData disciplinaOrigem;
	private DisciplinaData disciplinaDestino;
	private StatusAnaliseEquivalencia status;
	private boolean aprovado;
	private List<AlunoAnaliseEquivalenciaData> alunos;
}
