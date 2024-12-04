package com.marcospedroso.facens.correlato.mapper.data;

import java.util.UUID;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;
import com.marcospedroso.facens.correlato.model.AnaliseEquivalencia;
import com.marcospedroso.facens.correlato.model.Disciplina;
import com.marcospedroso.facens.correlato.model.Usuario;

public class AnaliseEquivalenciaDataMapper {
	
	 public static AnaliseEquivalenciaData fromEntityToDTO(AnaliseEquivalencia entity) {
		 AnaliseEquivalenciaData analiseEquivalenciaData = AnaliseEquivalenciaData.builder()
		            .id(entity.getId())
		            .status(entity.getStatus())
		            .aprovado(entity.isAprovado())
		            .build();

		    if (entity.getProfessorResponsavel() != null) {
		        analiseEquivalenciaData.setProfessorResponsavel(UsuarioDataMapper.fromEntityToDTO(entity.getProfessorResponsavel()));
		    }

		    if (entity.getDisciplinaOrigem() != null) {
		        analiseEquivalenciaData.setDisciplinaOrigem(DisciplinaDataMapper.fromEntityToDTO(entity.getDisciplinaOrigem()));
		    }

		    if (entity.getDisciplinaDestino() != null) {
		        analiseEquivalenciaData.setDisciplinaDestino(DisciplinaDataMapper.fromEntityToDTO(entity.getDisciplinaDestino()));
		    }

		    return analiseEquivalenciaData;
	 }
	 
	 public static AnaliseEquivalencia fromDTOCreateUpdateToEntity(CreateUpdateAnaliseEquivalencia dto) {
	        return AnaliseEquivalencia.builder()
	        		.id(dto.getId())
	                .professorResponsavel(new Usuario(UUID.fromString(dto.getIdProfessorResponsavel())))
	                .disciplinaOrigem(new Disciplina((long) dto.getIdDisciplinaOrigem()))
	                .disciplinaDestino(new Disciplina((long) dto.getIdDisciplinaDestino()))
	                .status(dto.getStatus())
	                .build();
	 }
}
