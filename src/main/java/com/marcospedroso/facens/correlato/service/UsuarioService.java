package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;

public interface UsuarioService {
	List<UsuarioData> findAll();

	UsuarioData findById(Long id);

	UsuarioData create(CreateUpdateUsuario dto);

	UsuarioData update(Long id, CreateUpdateUsuario dto);

    void delete(Long id);

    UsuarioData toggleStatus(Long id);
}
