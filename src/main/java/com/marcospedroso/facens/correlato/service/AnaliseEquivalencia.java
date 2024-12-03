package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;

public interface AnaliseEquivalencia {
	List<AnaliseEquivalenciaData> findAll();

	AnaliseEquivalenciaData findById(Long id);

	AnaliseEquivalenciaData create(CreateUpdateAnaliseEquivalencia dto);

	AnaliseEquivalenciaData update(Long id, CreateUpdateAnaliseEquivalencia dto);

    void delete(Long id);
}
