package com.marcospedroso.facens.correlato.dto.data;

import com.marcospedroso.facens.correlato.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioData {
	private String id;
    private String email;
    private String nome;
    private FaculdadeData faculdade;
	private TipoUsuario tipo;
	private boolean ativo;
}
