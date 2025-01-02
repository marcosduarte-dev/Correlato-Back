package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.CursoDataMapper;
import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.model.Faculdade;
import com.marcospedroso.facens.correlato.repository.CursoRepository;
import com.marcospedroso.facens.correlato.repository.FaculdadeRepository;
import com.marcospedroso.facens.correlato.service.CursoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService{
	
	private final CursoRepository repository;
	private final FaculdadeRepository faculdadeRepository;

	@Override
	public List<CursoData> findAll() {
		List<Curso> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum Curso cadastrado!");
		}
		
		return lista.stream()
				.map(CursoDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CursoData> findAllAtivos() {
		List<Curso> lista = repository.findAllByAtivo(true);
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum curso ativo encontrado!");
		}
		
		return lista.stream()
				.map(CursoDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CursoData> findByFaculdade(Long id) {
		Faculdade faculdade = getFaculdade(id);

		List<Curso> lista = repository.findByFaculdade(faculdade);
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum curso com essa faculdade encontrada!");
		}
		
		return lista.stream()
				.map(CursoDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CursoData findById(Long id) {
		return CursoDataMapper.fromEntityToDTO(getCurso(id));
	}

	@Override
	@Transactional
	public CursoData create(CreateUpdateCurso dto) {
		if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID deve ser nulo");
        }
		try {
			Curso entity = CursoDataMapper.fromDTOCreateUpdateToEntity(dto);
			entity.setAtivo(true);
			entity = repository.save(entity);
	       
			return CursoDataMapper.fromEntityToDTO(entity);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("violates unique constraint"))
				throw new DataIntegrityViolationException("Ja existe um curso com esse nome para essa faculdade.");
		
			throw new DataIntegrityViolationException(e.getLocalizedMessage());
		}
	}

	@Override
	@Transactional
	public CursoData update(CreateUpdateCurso dto) {
		getCurso(dto.getId());
		
		Curso entity = repository.save(CursoDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return CursoDataMapper.fromEntityToDTO(entity);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		getCurso(id);
		
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public CursoData toggleStatus(Long id) {
		Curso entity = getCurso(id);
		
		entity.setAtivo(!entity.isAtivo());
		
		entity = repository.save(entity);
		
		return CursoDataMapper.fromEntityToDTO(entity);
	}
	
	private Curso getCurso(Long id) {
        Optional<Curso> optional = repository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Curso não encontrado");
        }
        return optional.get();
    }

	private Faculdade getFaculdade(Long id) {
        Optional<Faculdade> optional = faculdadeRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Faculdade não encontrado");
        }
        return optional.get();
    }

}
