package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;

public interface AnaliseEquivalencia {
	List<AnaliseEquivalenciaData> findAll();

	AnaliseEquivalenciaData findById(Long id);

	AnaliseEquivalenciaData create(CreateAnaliseEquivalencia dto);

	AnaliseEquivalenciaData update(Long id, CreateAnaliseEquivalencia dto);

    void delete(Long id);
}
