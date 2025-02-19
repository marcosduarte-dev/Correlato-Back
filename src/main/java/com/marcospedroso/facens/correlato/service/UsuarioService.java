package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.LoginRequest;
import com.marcospedroso.facens.correlato.dto.LoginResponse;
import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;

public interface UsuarioService {
	List<UsuarioData> findAll();

	UsuarioData findById(String id);

	List<UsuarioData> findProfessorByFaculdadeId(Long faculdade_id);

	UsuarioData create(CreateUpdateUsuario dto);

	UsuarioData update(CreateUpdateUsuario dto);

    void delete(String id);

    UsuarioData toggleStatus(String id);
    
    LoginResponse login(LoginRequest dto);
}
