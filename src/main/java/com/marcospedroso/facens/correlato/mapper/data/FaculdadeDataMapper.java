package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateFaculdade;
import com.marcospedroso.facens.correlato.dto.data.FaculdadeData;
import com.marcospedroso.facens.correlato.model.Faculdade;

public class FaculdadeDataMapper {
	
	 public static FaculdadeData fromEntityToDTO(Faculdade entity) {
	        return FaculdadeData.builder()
	        		.id(entity.getId())
	                .nome(entity.getNome())
	                .ativo(entity.isAtivo())
	                .build();
	 }
	 
	 public static Faculdade fromDTOCreateUpdateToEntity(CreateUpdateFaculdade dto) {
	        return Faculdade.builder()
	        		.id(dto.getId())
	                .nome(dto.getNome())
	                .build();
	 }
}
