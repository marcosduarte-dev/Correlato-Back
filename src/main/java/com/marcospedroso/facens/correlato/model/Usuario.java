package com.marcospedroso.facens.correlato.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.marcospedroso.facens.correlato.enums.TipoUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String email;
	
	private String nome;
	
	private String senha;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_faculdade")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Faculdade faculdade;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	private boolean ativo;

}
