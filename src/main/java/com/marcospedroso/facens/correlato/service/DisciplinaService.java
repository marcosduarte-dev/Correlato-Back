package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateDisciplina;
import com.marcospedroso.facens.correlato.dto.data.DisciplinaData;

public interface DisciplinaService {
	List<DisciplinaData> findAll();

	DisciplinaData findById(Long id);

	DisciplinaData create(CreateDisciplina dto);

	DisciplinaData update(Long id, CreateDisciplina dto);

    void delete(Long id);

    DisciplinaData toggleStatus(Long id);
}
