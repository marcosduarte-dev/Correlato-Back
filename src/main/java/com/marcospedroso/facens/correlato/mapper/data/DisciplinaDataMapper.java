package com.marcospedroso.facens.correlato.mapper.data;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateDisciplina;
import com.marcospedroso.facens.correlato.dto.data.DisciplinaData;
import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.model.Disciplina;

public class DisciplinaDataMapper {
	
	 public static DisciplinaData fromEntityToDTO(Disciplina entity) {
		 DisciplinaData disciplinaData = DisciplinaData.builder()
		            .id(entity.getId())
		            .codDisciplina(entity.getCodDisciplina())
		            .nome(entity.getNome())
		            .cargaHoraria(entity.getCargaHoraria())
		            .ementa(entity.getEmenta())
		            .programa(entity.getPrograma())
		            .ativo(entity.isAtivo())
		            .build();

		    if (entity.getCurso() != null) {
		        disciplinaData.setCurso(CursoDataMapper.fromEntityToDTO(entity.getCurso()));
		    }

		    return disciplinaData;
	 }
	 
	 public static Disciplina fromDTOCreateUpdateToEntity(CreateUpdateDisciplina dto) {
	        return Disciplina.builder()
	        		.id(dto.getId())
	        		.codDisciplina(dto.getCodDisciplina())
	                .nome(dto.getNome())
	                .curso(new Curso((long) dto.getIdCurso()))
	                .cargaHoraria(dto.getCargaHoraria())
	                .ementa(dto.getEmenta())
	                .programa(dto.getPrograma())
	                .build();
	 }
}
