package com.marcospedroso.facens.correlato.service;

import java.util.List;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAluno;
import com.marcospedroso.facens.correlato.dto.data.AlunoData;

public interface AlunoService {
    List<AlunoData> findAll();

	AlunoData findById(Long id);

	AlunoData create(CreateUpdateAluno dto);

	AlunoData update(CreateUpdateAluno dto);

    void delete(Long id);
}
