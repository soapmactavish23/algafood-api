package com.hkprogrammer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hkprogrammer.algafood.domain.exception.EntidadeEmUsoException;
import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository repository;

	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			repository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			String msg = String.format("Não existe um cadastro de cozinha com código %d", cozinhaId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
		} catch (DataIntegrityViolationException e) {
			String msg = String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId);
			throw new EntidadeEmUsoException(msg);
		}
	}

}
