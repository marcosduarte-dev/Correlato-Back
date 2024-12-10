package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateAnaliseEquivalencia;
import com.marcospedroso.facens.correlato.dto.data.AnaliseEquivalenciaData;
import com.marcospedroso.facens.correlato.dto.events.IARequestEvent;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.AnaliseEquivalenciaDataMapper;
import com.marcospedroso.facens.correlato.model.AnaliseEquivalencia;
import com.marcospedroso.facens.correlato.model.Disciplina;
import com.marcospedroso.facens.correlato.model.Usuario;
import com.marcospedroso.facens.correlato.repository.AnaliseEquivalenciaRepository;
import com.marcospedroso.facens.correlato.repository.DisciplinaRepository;
import com.marcospedroso.facens.correlato.repository.UsuarioRepository;
import com.marcospedroso.facens.correlato.service.AnaliseEquivalenciaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnaliseEquivalenciaServiceImpl implements AnaliseEquivalenciaService{
	
	private final AnaliseEquivalenciaRepository repository;
	private final DisciplinaRepository disciplinaRepository;
	private final UsuarioRepository usuarioRepository;
	private final ApplicationEventPublisher eventPublisher;

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
		       

			
			AnaliseEquivalenciaData data = AnaliseEquivalenciaDataMapper.fromEntityToDTOCompleto(
				entity,
				getDisciplina(entity.getDisciplinaOrigem().getId()),
				getDisciplina(entity.getDisciplinaDestino().getId()),
				getUsuario(entity.getProfessorResponsavel().getId().toString()));

			eventPublisher.publishEvent(new IARequestEvent(data));

		    return data;
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

	private Disciplina getDisciplina(Long id) {
        Optional<Disciplina> optional = disciplinaRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Disciplina não encontrado");
        }
        return optional.get();
    }

	private Usuario getUsuario(String id) {
        Optional<Usuario> optional = usuarioRepository.findById(UUID.fromString(id));
        if(optional.isEmpty()) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return optional.get();
    }

}
