package com.marcospedroso.facens.correlato.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcospedroso.facens.correlato.enums.StatusAnaliseEquivalencia;

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
@Table(name = "tb_analiseEquivalencia")
public class AnaliseEquivalencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor_responsavel")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario professorResponsavel;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_disciplina_origem")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Disciplina disciplinaOrigem;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_disciplina_destino")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Disciplina disciplinaDestino;
	
	private StatusAnaliseEquivalencia status;
	
	private boolean aprovado;
}
