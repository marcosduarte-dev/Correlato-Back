package com.marcospedroso.facens.correlato.model;

import java.time.LocalDateTime;

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
@Table(name = "tb_ia_response")
public class IAResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_analise_equivalencia")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private AnaliseEquivalencia analiseEquivalencia;

	private String responseRaw;

	private String requestRaw;

	private String equivalencias;

	private String diferencas;

	private Boolean aprovado;

	private Float porcentagemEquivalencia;

	private LocalDateTime createdAt;
}
