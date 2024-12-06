package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.AnaliseEquivalenciaDataMapper;
import com.marcospedroso.facens.correlato.model.AnaliseEquivalencia;
import com.marcospedroso.facens.correlato.repository.AnaliseEquivalenciaRepository;
import com.marcospedroso.facens.correlato.service.AnaliseEquivalenciaService;

@Service
public class AnaliseEquivalenciaServiceImpl implements AnaliseEquivalenciaService{
	
	@Autowired
	private AnaliseEquivalenciaRepository repository;

	@Override
	public List<AnaliseEquivalenciaData> findAll() {
		List<AnaliseEquivalencia> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhuma AnaliseEquivalencia cadastrada!");
		}
		
		return lista.stream()
				.map(AnaliseEquivalenciaDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public AnaliseEquivalenciaData findById(Long id) {
		return AnaliseEquivalenciaDataMapper.fromEntityToDTO(getAnaliseEquivalencia(id));
	}

	@Override
	public AnaliseEquivalenciaData create(CreateUpdateAnaliseEquivalencia dto) {
		if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID deve ser nulo");
        }
		
		try {
			AnaliseEquivalencia entity = repository.save(
					AnaliseEquivalenciaDataMapper.fromDTOCreateUpdateToEntity(dto));
		       
		    return AnaliseEquivalenciaDataMapper.fromEntityToDTO(entity);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("violates unique constraint"))
				throw new DataIntegrityViolationException("Ja existe uma analise entre essas duas disciplinas");
			
			throw new DataIntegrityViolationException(e.getLocalizedMessage());
		}

		
	}

	@Override
	public AnaliseEquivalenciaData update(CreateUpdateAnaliseEquivalencia dto) {
		getAnaliseEquivalencia(dto.getId());
		
		AnaliseEquivalencia entity = repository.save(AnaliseEquivalenciaDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return AnaliseEquivalenciaDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public void delete(Long id) {
		getAnaliseEquivalencia(id);
		
		repository.deleteById(id);
	}
	
	private AnaliseEquivalencia getAnaliseEquivalencia(Long id) {
        Optional<AnaliseEquivalencia> optional = repository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("AnaliseEquivalencia não encontrado");
        }
        return optional.get();
    }

}
