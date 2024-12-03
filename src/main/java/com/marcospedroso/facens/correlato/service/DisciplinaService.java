package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateDisciplina;
import com.marcospedroso.facens.correlato.dto.data.DisciplinaData;

public interface DisciplinaService {
	List<DisciplinaData> findAll();

	DisciplinaData findById(Long id);

	DisciplinaData create(CreateUpdateDisciplina dto);

	DisciplinaData update(Long id, CreateUpdateDisciplina dto);

    void delete(Long id);

    DisciplinaData toggleStatus(Long id);
}
