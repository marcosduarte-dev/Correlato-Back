package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateCurso;
import com.marcospedroso.facens.correlato.dto.data.CursoData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.CursoDataMapper;
import com.marcospedroso.facens.correlato.model.Curso;
import com.marcospedroso.facens.correlato.repository.CursoRepository;
import com.marcospedroso.facens.correlato.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	private CursoRepository repository;

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
	public CursoData findById(Long id) {
		return CursoDataMapper.fromEntityToDTO(getCurso(id));
	}

	@Override
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
	public CursoData update(CreateUpdateCurso dto) {
		getCurso(dto.getId());
		
		Curso entity = repository.save(CursoDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return CursoDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public void delete(Long id) {
		getCurso(id);
		
		repository.deleteById(id);
	}

	@Override
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

}
