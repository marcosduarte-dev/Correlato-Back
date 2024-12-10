package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateFaculdade;
import com.marcospedroso.facens.correlato.dto.data.FaculdadeData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.FaculdadeDataMapper;
import com.marcospedroso.facens.correlato.model.Faculdade;
import com.marcospedroso.facens.correlato.repository.FaculdadeRepository;
import com.marcospedroso.facens.correlato.service.FaculdadeService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaculdadeServiceImpl implements FaculdadeService{
	
	private final FaculdadeRepository repository;

	@Override
	public List<FaculdadeData> findAll() {
		List<Faculdade> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhuma Faculdade cadastrada!");
		}
		
		return lista.stream()
				.map(FaculdadeDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public FaculdadeData findById(Long id) {
		return FaculdadeDataMapper.fromEntityToDTO(getFaculdade(id));
	}

	@Override
	@Transactional
	public FaculdadeData create(CreateUpdateFaculdade dto) {
		if(Objects.nonNull(dto.getId())) {
			throw new BadRequestException("ID deve ser nulo");
		}
		
		try {
			Faculdade entity = FaculdadeDataMapper.fromDTOCreateUpdateToEntity(dto);
			entity.setAtivo(true);
			entity = repository.save(entity);
			
			return FaculdadeDataMapper.fromEntityToDTO(entity);
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("violates unique constraint"))
				throw new DataIntegrityViolationException("Ja existe uma faculdade com esse nome");
			
			throw new DataIntegrityViolationException(e.getLocalizedMessage());
		}
	}

	@Override
	@Transactional
	public FaculdadeData update(CreateUpdateFaculdade dto) {
		getFaculdade(dto.getId());
		
		Faculdade entity = repository.save(FaculdadeDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return FaculdadeDataMapper.fromEntityToDTO(entity);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		getFaculdade(id);
		
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public FaculdadeData toggleStatus(Long id) {
		Faculdade entity = getFaculdade(id);
		
		entity.setAtivo(!entity.isAtivo());
		
		entity = repository.save(entity);
		
		return FaculdadeDataMapper.fromEntityToDTO(entity);
	}
	
	private Faculdade getFaculdade(Long id) {
        Optional<Faculdade> optional = repository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Faculdade não encontrada");
        }
        return optional.get();
    }

}
