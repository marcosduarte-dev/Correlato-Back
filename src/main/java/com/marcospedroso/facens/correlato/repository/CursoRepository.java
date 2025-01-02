package com.marcospedroso.facens.correlato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.model.Faculdade;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
    List<Curso> findAllByAtivo(boolean ativo);
    List<Curso> findByFaculdade(Faculdade faculdade);
}
