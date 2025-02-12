package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAluno;
import com.marcospedroso.facens.correlato.dto.data.AlunoData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.AlunoDataMapper;
import com.marcospedroso.facens.correlato.model.Aluno;
import com.marcospedroso.facens.correlato.repository.AlunoRepository;
import com.marcospedroso.facens.correlato.service.AlunoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService{

	private final AlunoRepository repository;

	@Override
	public List<AlunoData> findAll() {
		List<Aluno> lista = repository.findAll();

		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum Aluno cadastrado!");
		}

		return lista.stream()
				.map(AlunoDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public AlunoData findById(Long id) {
		return AlunoDataMapper.fromEntityToDTO(getAluno(id));
	}

	@Override
	public AlunoData create(CreateUpdateAluno dto) {

		if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID deve ser nulo");
        }

		try {
			Aluno entity = AlunoDataMapper.fromDTOCreateUpdateToEntity(dto);
	    	entity = repository.save(entity);

			AlunoData data = AlunoDataMapper.fromEntityToDTO(entity);

	    	return data;
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("violates unique constraint"))
				throw new DataIntegrityViolationException("Ja existe um aluno com esse email.");
			throw new DataIntegrityViolationException(e.getLocalizedMessage());
		}
	}

	@Override
	@Transactional
	public AlunoData update(CreateUpdateAluno dto) {
		getAluno(dto.getId());

		Aluno entity = repository.save(AlunoDataMapper.fromDTOCreateUpdateToEntity(dto));

		return AlunoDataMapper.fromEntityToDTO(entity);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		getAluno(id);

		repository.deleteById(id);
	}

	private Aluno getAluno(Long id) {
        Optional<Aluno> optional = repository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return optional.get();
    }
}
