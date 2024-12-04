package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;

public interface AnaliseEquivalenciaService {
	List<AnaliseEquivalenciaData> findAll();

	AnaliseEquivalenciaData findById(Long id);

	AnaliseEquivalenciaData create(CreateUpdateAnaliseEquivalencia dto);

	AnaliseEquivalenciaData update(CreateUpdateAnaliseEquivalencia dto);

    void delete(Long id);
}
