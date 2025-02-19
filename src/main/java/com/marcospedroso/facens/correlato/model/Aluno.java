package com.marcospedroso.facens.correlato.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tb_aluno")
public class Aluno {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Curso curso;

    private String email;
    private String matricula;

    @ManyToMany
    @JoinTable(
        name = "tb_aluno_analise_equivalencia", 
        joinColumns = @JoinColumn(name = "aluno_id"), 
        inverseJoinColumns = @JoinColumn(name = "analise_equivalencia_id")
    )
    @JsonManagedReference
    private List<AnaliseEquivalencia> analisesEquivalencias = new ArrayList<>();

}
