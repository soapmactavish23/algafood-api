package com.hkprogrammer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.hkprogrammer.algafood.domain.exception.EntidadeEmUsoException;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository repository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}

	@Transactional
	public void excluir(Long cozinhaId) {
	    try {
	        repository.deleteById(cozinhaId);
	        
	    } catch (EmptyResultDataAccessException e) {
	        throw new CozinhaNaoEncontradaException(cozinhaId);
	    
	    } catch (DataIntegrityViolationException e) {
	        throw new EntidadeEmUsoException(
	            String.format(MSG_COZINHA_EM_USO, cozinhaId));
	    }
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
	    return repository.findById(cozinhaId)
	        .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
