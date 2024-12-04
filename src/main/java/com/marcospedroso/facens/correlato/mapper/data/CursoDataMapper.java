package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;
import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.model.Faculdade;

public class CursoDataMapper {
	
	 public static CursoData fromEntityToDTO(Curso entity) {
	        return CursoData.builder()
	        		.id(entity.getId())
	                .nome(entity.getNome())
	                .faculdade(FaculdadeDataMapper.fromEntityToDTO(entity.getFaculdade()))
	                .ativo(entity.isAtivo())
	                .build();
	 }
	 
	 public static Curso fromDTOCreateUpdateToEntity(CreateUpdateCurso dto) {
	        return Curso.builder()
	        		.id(dto.getId())
	                .nome(dto.getNome())
	                .faculdade(new Faculdade((long) dto.getIdFaculdade()))
	                .build();
	 }
}
