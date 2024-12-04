package com.marcospedroso.facens.correlato.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

}
