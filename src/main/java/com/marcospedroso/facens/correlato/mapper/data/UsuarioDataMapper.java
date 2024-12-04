package com.marcospedroso.facens.correlato.mapper.data;

import java.util.UUID;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;
import com.marcospedroso.facens.correlato.model.Usuario;
import com.marcospedroso.facens.correlato.model.Faculdade;

public class UsuarioDataMapper {
	
	 public static UsuarioData fromEntityToDTO(Usuario entity) {
		 UsuarioData usuarioData = UsuarioData.builder()
		            .id(entity.getId().toString())
		            .email(entity.getEmail())
		            .nome(entity.getNome())
		            .tipo(entity.getTipo())
		            .ativo(entity.isAtivo())
		            .build();

		    if (entity.getFaculdade() != null) {
		        usuarioData.setFaculdade(FaculdadeDataMapper.fromEntityToDTO(entity.getFaculdade()));
		    }

		    return usuarioData;
	 }
	 
	 public static Usuario fromDTOCreateUpdateToEntity(CreateUpdateUsuario dto) {
	        Usuario usuario = Usuario.builder()
	        		.email(dto.getEmail())
	                .nome(dto.getNome())
	                .senha(dto.getSenha())
	                .faculdade(new Faculdade((long) dto.getIdFaculdade()))
	                .tipo(dto.getTipo())
	                .build();
	        
	        if (dto.getId() != null) {
	        	usuario.setId(UUID.fromString(dto.getId()));
	        }
	        
	        return usuario;
	 }
}
