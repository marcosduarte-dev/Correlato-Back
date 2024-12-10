package com.marcospedroso.facens.correlato.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@NotBlank(message = "não pode ser vazio")
    private String email;

	@NotBlank(message = "não pode ser vazio")
	private String senha;
}
