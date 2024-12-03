package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;

public interface CursoService {
	List<CursoData> findAll();

	CursoData findById(Long id);

	CursoData create(CreateCurso dto);

	CursoData update(Long id, CreateCurso dto);

    void delete(Long id);

    CursoData toggleStatus(Long id);
}
