package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateDisciplina;
import com.marcospedroso.facens.correlato.dto.data.DisciplinaData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.DisciplinaDataMapper;
import com.marcospedroso.facens.correlato.model.Disciplina;
import com.marcospedroso.facens.correlato.repository.DisciplinaRepository;
import com.marcospedroso.facens.correlato.service.DisciplinaService;

@Service
public class DisciplinaServiceImpl implements DisciplinaService{
	
	@Autowired
	private DisciplinaRepository repository;

	@Override
	public List<DisciplinaData> findAll() {
		List<Disciplina> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhuma Disciplina cadastrada!");
		}
		
		return lista.stream()
				.map(DisciplinaDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public DisciplinaData findById(Long id) {
		return DisciplinaDataMapper.fromEntityToDTO(getDisciplina(id));
	}

	@Override
	public DisciplinaData create(CreateUpdateDisciplina dto) {
		if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID deve ser nulo");
        }

       Disciplina entity = DisciplinaDataMapper.fromDTOCreateUpdateToEntity(dto);
       entity.setAtivo(true);
       repository.save(entity);
       
       return DisciplinaDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public DisciplinaData update(CreateUpdateDisciplina dto) {
		getDisciplina(dto.getId());
		
		Disciplina entity = repository.save(DisciplinaDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return DisciplinaDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public void delete(Long id) {
		getDisciplina(id);
		
		repository.deleteById(id);
	}

	@Override
	public DisciplinaData toggleStatus(Long id) {
		Disciplina entity = getDisciplina(id);
		
		entity.setAtivo(!entity.isAtivo());
		
		entity = repository.save(entity);
		
		return DisciplinaDataMapper.fromEntityToDTO(entity);
	}
	
	private Disciplina getDisciplina(Long id) {
        Optional<Disciplina> optional = repository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        return optional.get();
    }

}