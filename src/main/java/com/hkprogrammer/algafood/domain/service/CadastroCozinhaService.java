package com.hkprogrammer.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.exception.EntidadeEmUsoException;
import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
	@Autowired
	private CozinhaRepository repository;

	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			repository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			String msg = String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId);
			throw new EntidadeNaoEncontradaException(msg);
		} catch (DataIntegrityViolationException e) {
			String msg = String.format(MSG_COZINHA_EM_USO, cozinhaId);
			throw new EntidadeEmUsoException(msg);
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return repository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	}

}
