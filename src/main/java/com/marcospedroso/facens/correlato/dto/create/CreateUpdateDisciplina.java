package com.marcospedroso.facens.correlato.dto.create;

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
public class CreateUpdateDisciplina {
	
	private Long id;
	
	@NotBlank(message = "não pode ser vazio")
    private String codDisciplina;
	
	@NotBlank(message = "não pode ser vazio")
    private String nome;
	
	@NotNull(message = "não pode ser nulo")
	private Long idCurso;
	
	@NotNull(message = "não pode ser nulo")
    private Float cargaHoraria;
	
	@NotBlank(message = "não pode ser vazio")
    private String ementa;
	
    private String programa;
}
