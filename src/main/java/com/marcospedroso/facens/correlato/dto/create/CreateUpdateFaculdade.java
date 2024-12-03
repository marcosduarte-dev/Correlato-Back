package com.marcospedroso.facens.correlato.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateFaculdade {
	private Long id;
	
	@NotBlank(message = "não pode ser vazio")
    private String nome;
}
