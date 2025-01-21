package com.marcospedroso.facens.correlato.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateDisciplina;
import com.marcospedroso.facens.correlato.dto.data.DisciplinaData;
import com.marcospedroso.facens.correlato.service.DisciplinaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {
	
	private final DisciplinaService service;
	
	@GetMapping
    public ResponseEntity<List<DisciplinaData>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<DisciplinaData>> findAllAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllAtivos());
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<DisciplinaData>> findByCursoId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCursoId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaData> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaData> create(@Valid @RequestBody CreateUpdateDisciplina dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaData> update(@Valid @PathVariable Long id, @RequestBody CreateUpdateDisciplina dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisciplinaData> toggleStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.toggleStatus(id));
    }

}
