package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateFaculdade;
import com.marcospedroso.facens.correlato.dto.data.FaculdadeData;

public interface FaculdadeService {
	List<FaculdadeData> findAll();

	FaculdadeData findById(Long id);

	FaculdadeData create(CreateFaculdade dto);

	FaculdadeData update(Long id, CreateFaculdade dto);

    void delete(Long id);

    FaculdadeData toggleStatus(Long id);
}
