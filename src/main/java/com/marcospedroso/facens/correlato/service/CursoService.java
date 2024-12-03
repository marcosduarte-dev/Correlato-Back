package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;

public interface CursoService {
	List<CursoData> findAll();

	CursoData findById(Long id);

	CursoData create(CreateUpdateCurso dto);

	CursoData update(CreateUpdateCurso dto);

    void delete(Long id);

    CursoData toggleStatus(Long id);
}
