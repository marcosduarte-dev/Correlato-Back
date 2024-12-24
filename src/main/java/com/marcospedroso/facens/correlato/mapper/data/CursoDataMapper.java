package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;
import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.model.Faculdade;

public class CursoDataMapper {
	
	 public static CursoData fromEntityToDTO(Curso entity) {
		 CursoData cursoData = CursoData.builder()
		            .id(entity.getId())
		            .nome(entity.getNome())
		            .ativo(entity.isAtivo())
		            .build();
		 
		 if (entity.getFaculdade() != null) {
			 cursoData.setFaculdade(FaculdadeDataMapper.fromEntityToDTO(entity.getFaculdade()));
		 }

		 return cursoData;
	 }
	 
	 public static Curso fromDTOCreateUpdateToEntity(CreateUpdateCurso dto) {
	        return Curso.builder()
	        		.id(dto.getId())
	                .nome(dto.getNome())
	                .faculdade(new Faculdade((long) dto.getIdFaculdade()))
					.ativo(true)
	                .build();
	 }
}
