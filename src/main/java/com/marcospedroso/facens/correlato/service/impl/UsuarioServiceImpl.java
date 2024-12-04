package com.marcospedroso.facens.correlato.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.UsuarioDataMapper;
import com.marcospedroso.facens.correlato.model.Usuario;
import com.marcospedroso.facens.correlato.repository.UsuarioRepository;
import com.marcospedroso.facens.correlato.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public List<UsuarioData> findAll() {
		List<Usuario> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum Usuario cadastrado!");
		}
		
		return lista.stream()
				.map(UsuarioDataMapper::fromEntityToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public UsuarioData findById(String id) {
		return UsuarioDataMapper.fromEntityToDTO(getUsuario(id));
	}

	@Override
	public UsuarioData create(CreateUpdateUsuario dto) {
		if(Objects.nonNull(dto.getId())) {
            throw new BadRequestException("ID deve ser nulo");
        }

       Usuario entity = UsuarioDataMapper.fromDTOCreateUpdateToEntity(dto);
       entity.setAtivo(true);
       entity = repository.save(entity);
       
       return UsuarioDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public UsuarioData update(CreateUpdateUsuario dto) {
		getUsuario(dto.getId());
		
		Usuario entity = repository.save(UsuarioDataMapper.fromDTOCreateUpdateToEntity(dto));
		
		return UsuarioDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public void delete(String id) {
		getUsuario(id);
		
		repository.deleteById(UUID.fromString(id));
	}

	@Override
	public UsuarioData toggleStatus(String id) {
		Usuario entity = getUsuario(id);
		
		entity.setAtivo(!entity.isAtivo());
		
		entity = repository.save(entity);
		
		return UsuarioDataMapper.fromEntityToDTO(entity);
	}
	
	private Usuario getUsuario(String id) {
        Optional<Usuario> optional = repository.findById(UUID.fromString(id));
        if(optional.isEmpty()) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return optional.get();
    }

}
