package com.hkprogrammer.algafood.domain.repository;

import java.util.List;

import com.hkprogrammer.algafood.domain.models.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
		
}
