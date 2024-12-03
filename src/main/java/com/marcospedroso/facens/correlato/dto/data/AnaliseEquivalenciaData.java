package com.marcospedroso.facens.correlato.dto.data;

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
	private UsuarioData idProfessorResponsavel;
	private DisciplinaData idDisciplinaOrigem;
	private DisciplinaData idDisciplinaDestino;
	private StatusAnaliseEquivalencia status;
}
