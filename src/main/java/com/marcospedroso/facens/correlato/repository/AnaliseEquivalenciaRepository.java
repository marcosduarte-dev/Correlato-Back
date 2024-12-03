package com.marcospedroso.facens.correlato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.AnaliseDeEquivalencia;

@Repository
public interface AnaliseEquivalenciaRepository extends JpaRepository<AnaliseDeEquivalencia, Long>{

}
