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

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateFaculdade;
import com.marcospedroso.facens.correlato.dto.data.FaculdadeData;
import com.marcospedroso.facens.correlato.service.FaculdadeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/faculdades")
@RequiredArgsConstructor
public class FaculdadeController {
	
	private final FaculdadeService service;
	
	@GetMapping
    public ResponseEntity<List<FaculdadeData>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<FaculdadeData>> findAllAtivos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaculdadeData> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FaculdadeData> create(@Valid @RequestBody CreateUpdateFaculdade dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaculdadeData> update(@Valid @PathVariable Long id, @RequestBody CreateUpdateFaculdade dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FaculdadeData> toggleStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.toggleStatus(id));
    }

}
