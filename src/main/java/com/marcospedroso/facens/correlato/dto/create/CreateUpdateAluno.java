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
public class CreateUpdateAluno {
	
	private Long id;
	
	@NotBlank(message = "não pode ser vazio")
    private String email;
	
	@NotBlank(message = "não pode ser vazio")
    private String nome;

	@NotBlank(message = "não pode ser vazio")
	private String matricula;
	
	@NotNull(message = "não pode ser nulo")
    private Long idCurso;
}
