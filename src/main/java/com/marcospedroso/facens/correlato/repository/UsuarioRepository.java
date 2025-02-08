package com.marcospedroso.facens.correlato.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.Usuario;
import java.util.List;
import com.marcospedroso.facens.correlato.enums.TipoUsuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

	Optional<Usuario> findByEmail(String email);
	List<Usuario> findByTipoAndFaculdadeId(TipoUsuario tipo, Long faculdadeId);

}
