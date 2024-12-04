package com.marcospedroso.facens.correlato.dto.create;

import com.marcospedroso.facens.correlato.enums.StatusAnaliseEquivalencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateAnaliseEquivalencia {
	
	private long id;
	
	@NotBlank(message = "não pode ser vazio")
	private String idProfessorResponsavel;
	
	@NotNull(message = "não pode ser nulo")
	private int idDisciplinaOrigem;
	
	@NotNull(message = "não pode ser nulo")
	private int idDisciplinaDestino;
	
	@NotBlank(message = "não pode ser vazio")
	private StatusAnaliseEquivalencia status;
}
