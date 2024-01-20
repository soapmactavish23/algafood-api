package com.hkprogrammer.algafood.domain.repository;

import java.util.List;

import com.hkprogrammer.algafood.domain.models.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
	
	
}
