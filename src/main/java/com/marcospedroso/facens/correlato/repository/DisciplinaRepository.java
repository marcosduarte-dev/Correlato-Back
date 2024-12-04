package com.marcospedroso.facens.correlato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcospedroso.facens.correlato.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

}
