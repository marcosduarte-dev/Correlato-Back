package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;

public interface UsuarioService {
	List<UsuarioData> findAll();

	UsuarioData findById(Long id);

	UsuarioData create(CreateUsuario dto);

	UsuarioData update(Long id, CreateUsuario dto);

    void delete(Long id);

    UsuarioData toggleStatus(Long id);
}
