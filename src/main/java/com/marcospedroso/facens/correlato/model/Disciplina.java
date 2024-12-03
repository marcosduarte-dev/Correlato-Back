package com.marcospedroso.facens.correlato.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_disciplina")
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String codDisciplina;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Curso curso;
	
	private Float cargaHoraria;
	
	private String ementa;
	
	private String programa;
	
	private boolean ativo;
}
