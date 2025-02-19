package com.marcospedroso.facens.correlato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.Faculdade;

@Repository
public interface FaculdadeRepository extends JpaRepository<Faculdade, Long>{
    List<Faculdade> findAllByAtivo(boolean ativo);
}
