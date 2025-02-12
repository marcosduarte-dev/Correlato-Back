package com.marcospedroso.facens.correlato.mapper.data;

import java.util.stream.Collectors;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAluno;
import com.marcospedroso.facens.correlato.dto.data.AlunoData;
import com.marcospedroso.facens.correlato.model.Aluno;
import com.marcospedroso.facens.correlato.model.AnaliseEquivalencia;
import com.marcospedroso.facens.correlato.model.Curso;

public class AlunoDataMapper {
	
	 public static AlunoData fromEntityToDTO(Aluno entity) {
        AlunoData alunoData = AlunoData.builder()
		            .id(entity.getId().toString())
		            .email(entity.getEmail())
		            .nome(entity.getNome())
		            .matricula(entity.getMatricula())
		            .build();

		    if (entity.getCurso() != null) {
		        alunoData.setCurso(CursoDataMapper.fromEntityToDTO(entity.getCurso()));
		    }

			if (entity.getAnalisesEquivalencias() != null && !entity.getAnalisesEquivalencias().isEmpty()) {
				alunoData.setAnalisesEquivalencias(
					entity.getAnalisesEquivalencias().stream()
						.map(AnaliseEquivalenciaDataMapper::fromEntityToDTO)
						.collect(Collectors.toList())
				);
			}

		    return alunoData;
	 }
	 
	 public static Aluno fromDTOCreateUpdateToEntity(CreateUpdateAluno dto) {
            Aluno aluno = Aluno.builder()
                .id(dto.getId())
	        	.email(dto.getEmail())
	            .nome(dto.getNome())
	            .matricula(dto.getMatricula())
	            .curso(new Curso((long) dto.getIdCurso()))
	            .build();
	        
			if (dto.getAnalisesEquivalencias() != null && !dto.getAnalisesEquivalencias().isEmpty()) {
				aluno.setAnalisesEquivalencias(
					dto.getAnalisesEquivalencias().stream()
						.map(id -> new AnaliseEquivalencia(id))
						.collect(Collectors.toList())
				);
			}

	        return aluno;
	 }
}
