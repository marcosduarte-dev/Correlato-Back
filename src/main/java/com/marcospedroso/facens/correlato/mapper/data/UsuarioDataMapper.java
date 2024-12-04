package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;
import com.marcospedroso.facens.correlato.model.Usuario;
import com.marcospedroso.facens.correlato.model.Faculdade;

public class UsuarioDataMapper {
	
	 public static UsuarioData fromEntityToDTO(Usuario entity) {
	        return UsuarioData.builder()
	        		.id(entity.getId())
	        		.email(entity.getEmail())
	                .nome(entity.getNome())
	                .faculdade(FaculdadeDataMapper.fromEntityToDTO(entity.getFaculdade()))
	                .tipo(entity.getTipo())
	                .ativo(entity.isAtivo())
	                .build();
	 }
	 
	 public static Usuario fromDTOCreateUpdateToEntity(CreateUpdateUsuario dto) {
	        return Usuario.builder()
	        		.id(dto.getId())
	        		.email(dto.getEmail())
	                .nome(dto.getNome())
	                .senha(dto.getSenha())
	                .faculdade(new Faculdade((long) dto.getIdFaculdade()))
	                .tipo(dto.getTipo())
	                .build();
	 }
}
