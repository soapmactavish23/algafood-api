package com.hkprogrammer.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;
import com.hkprogrammer.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();
		cozinhaExists(cozinhaId);

		return repository.salvar(restaurante);
	}

	public void cozinhaExists(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);		

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}
	}

	public Restaurante atualizar(Long id, Restaurante obj) {
		Restaurante objSaved = buscar(id);
		
		Long cozinhaId = obj.getCozinha().getId();
		cozinhaExists(cozinhaId);
		
		BeanUtils.copyProperties(objSaved, obj, "id");
		

		return repository.salvar(objSaved);
	}
	
	public Restaurante buscar(Long id) {
		Restaurante restaurante = repository.buscar(id);
		if(restaurante == null) {
			throw new EntidadeNaoEncontradaException("Restaurante não encontrado");
		}
		
		return restaurante;
	}

}
