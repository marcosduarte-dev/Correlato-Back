package com.marcospedroso.facens.correlato.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.marcospedroso.facens.correlato.dto.EmailCriacaoUsuarioDto;
import com.marcospedroso.facens.correlato.dto.LoginRequest;
import com.marcospedroso.facens.correlato.dto.LoginResponse;
import com.marcospedroso.facens.correlato.dto.create.CreateUpdateUsuario;
import com.marcospedroso.facens.correlato.dto.data.UsuarioData;
import com.marcospedroso.facens.correlato.dto.events.EmailEvent;
import com.marcospedroso.facens.correlato.exception.BadRequestException;
import com.marcospedroso.facens.correlato.exception.NotFoundException;
import com.marcospedroso.facens.correlato.mapper.data.UsuarioDataMapper;
import com.marcospedroso.facens.correlato.model.Usuario;
import com.marcospedroso.facens.correlato.repository.UsuarioRepository;
import com.marcospedroso.facens.correlato.service.UsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository repository;
	private final JwtEncoder jwtEncoder;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ApplicationEventPublisher eventPublisher;

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

		try {
			Usuario entity = UsuarioDataMapper.fromDTOCreateUpdateToEntity(dto);
	    	entity.setAtivo(true);
	    	entity.setSenha(passwordEncoder.encode(entity.getSenha()));
	    	entity = repository.save(entity);

			UsuarioData data = UsuarioDataMapper.fromEntityToDTO(entity);

			if(!Objects.isNull(data)) {

				EmailCriacaoUsuarioDto emailDto = new EmailCriacaoUsuarioDto(
					data.getEmail(),
					data.getNome(),
					dto.getSenha(),
					data.getTipo().toString()
				);

				eventPublisher.publishEvent(new EmailEvent(emailDto));

			}

	    	return data;
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("violates unique constraint"))
				throw new DataIntegrityViolationException("Ja existe um usuario com esse email.");
			throw new DataIntegrityViolationException(e.getLocalizedMessage());
		}
	}

	@Override
	@Transactional
	public UsuarioData update(CreateUpdateUsuario dto) {
		getUsuario(dto.getId());

		Usuario entity = repository.save(UsuarioDataMapper.fromDTOCreateUpdateToEntity(dto));

		return UsuarioDataMapper.fromEntityToDTO(entity);
	}

	@Override
	@Transactional
	public void delete(String id) {
		getUsuario(id);

		repository.deleteById(UUID.fromString(id));
	}

	@Override
	@Transactional
	public UsuarioData toggleStatus(String id) {
		Usuario entity = getUsuario(id);

		entity.setAtivo(!entity.isAtivo());

		entity = repository.save(entity);

		return UsuarioDataMapper.fromEntityToDTO(entity);
	}

	@Override
	public LoginResponse login(LoginRequest dto) {
		var entity = repository.findByEmail(dto.getEmail());

		if(entity.isEmpty() || !entity.get().isLoginCorrect(dto, passwordEncoder)) {
			throw new BadCredentialsException("Usuario ou senha invalidos!");
		}

		var now = Instant.now();
		var expiresIn = 3600L;

		var claims = JwtClaimsSet.builder()
				.issuer("correlato-api")
				.subject(entity.get().getId().toString())
				.expiresAt(now.plusSeconds(expiresIn))
				.issuedAt(now)
				.claim("scope", entity.get().getTipo())
				.build();

		var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
		return new LoginResponse(jwtToken, expiresIn);
	}

	private Usuario getUsuario(String id) {
        Optional<Usuario> optional = repository.findById(UUID.fromString(id));
        if(optional.isEmpty()) {
            throw new NotFoundException("Usuario não encontrado");
        }
        return optional.get();
    }
}
