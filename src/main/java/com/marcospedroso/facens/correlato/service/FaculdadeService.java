package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateFaculdade;
import com.marcospedroso.facens.correlato.dto.data.FaculdadeData;

public interface FaculdadeService {
	List<FaculdadeData> findAll();

	List<FaculdadeData> findAllAtivos();

	FaculdadeData findById(Long id);

	FaculdadeData create(CreateUpdateFaculdade dto);

	FaculdadeData update(CreateUpdateFaculdade dto);

    void delete(Long id);

    FaculdadeData toggleStatus(Long id);
}
