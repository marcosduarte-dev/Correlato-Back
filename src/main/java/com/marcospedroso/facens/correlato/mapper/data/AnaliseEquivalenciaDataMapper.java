package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;
import com.marcospedroso.facens.correlato.model.AnaliseEquivalencia;
import com.marcospedroso.facens.correlato.model.Disciplina;
import com.marcospedroso.facens.correlato.model.Usuario;

public class AnaliseEquivalenciaDataMapper {
	
	 public static AnaliseEquivalenciaData fromEntityToDTO(AnaliseEquivalencia entity) {
	        return AnaliseEquivalenciaData.builder()
	        		.id(entity.getId())
	        		.professorResponsavel(UsuarioDataMapper.fromEntityToDTO(entity.getProfessorResponsavel()))
	                .disciplinaOrigem(DisciplinaDataMapper.fromEntityToDTO(entity.getDisciplinaOrigem()))
	                .disciplinaDestino(DisciplinaDataMapper.fromEntityToDTO(entity.getDisciplinaDestino()))
	                .status(entity.getStatus())
	                .aprovado(entity.isAprovado())
	                .build();
	 }
	 
	 public static AnaliseEquivalencia fromDTOCreateUpdateToEntity(CreateUpdateAnaliseEquivalencia dto) {
	        return AnaliseEquivalencia.builder()
	        		.id(dto.getId())
	                .professorResponsavel(new Usuario(dto.getIdProfessorResponsavel()))
	                .disciplinaOrigem(new Disciplina((long) dto.getIdDisciplinaOrigem()))
	                .disciplinaDestino(new Disciplina((long) dto.getIdDisciplinaDestino()))
	                .status(dto.getStatus())
	                .build();
	 }
}
