package com.marcospedroso.facens.correlato.dto.create;

import com.marcospedroso.facens.correlato.enums.TipoUsuario;

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
public class CreateUpdateUsuario {
	
	private String id;
	
	@NotBlank(message = "não pode ser vazio")
    private String email;
	
	@NotBlank(message = "não pode ser vazio")
    private String nome;
	
	@NotBlank(message = "não pode ser vazio")
    private String senha;
	
	@NotNull(message = "não pode ser nulo")
    private int idFaculdade;

	@NotBlank
	private TipoUsuario tipo;
}
