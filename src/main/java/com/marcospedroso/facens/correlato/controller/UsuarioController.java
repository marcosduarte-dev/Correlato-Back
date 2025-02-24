package com.marcospedroso.facens.correlato.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcospedroso.facens.correlato.dto.LoginRequest;
import com.marcospedroso.facens.correlato.dto.LoginResponse;
import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;
import com.marcospedroso.facens.correlato.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;

	@GetMapping
	// @PreAuthorize("hasAuthority('SCOPE_SECRETARIO')")
    public ResponseEntity<List<UsuarioData>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioData> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/professores/{id}")
    public ResponseEntity<List<UsuarioData>> findProfessorByFaculdadeId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findProfessorByFaculdadeId(id));
    }
    

    @PostMapping
    public ResponseEntity<UsuarioData> create(@Valid @RequestBody CreateUpdateUsuario dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioData> update(@Valid @PathVariable Long id, @RequestBody CreateUpdateUsuario dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest dto) {
    	return ResponseEntity.status(HttpStatus.OK).body(service.login(dto));
    }

}
