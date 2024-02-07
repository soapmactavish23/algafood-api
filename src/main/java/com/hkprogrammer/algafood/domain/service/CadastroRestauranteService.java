package com.hkprogrammer.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;

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
	
	public List<Restaurante> findByTaxa(BigDecimal tx1, BigDecimal tx2) {
		return repository.findByTaxaFreteBetween(tx1, tx2);
	}
	
	public List<Restaurante> listar() {
		return repository.findAll();
	}

	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();
		cozinhaExists(cozinhaId);

		return repository.save(restaurante);
	}

	public void cozinhaExists(Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).get();		

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}
	}

	public Restaurante atualizar(Long id, Restaurante obj) {
		Restaurante objSaved = buscar(id);
		
		Long cozinhaId = objSaved.getCozinha().getId();
		cozinhaExists(cozinhaId);
		
		BeanUtils.copyProperties(obj, objSaved, "id");
		

		return repository.save(objSaved);
	}
	
	public Restaurante buscar(Long id) {
		Restaurante restaurante = repository.findById(id).get();
		if(restaurante == null) {
			throw new EntidadeNaoEncontradaException("Restaurante não encontrado");
		}
		
		return restaurante;
	}

}
