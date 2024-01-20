package com.hkprogrammer.algafood.domain.repository;

import java.util.List;

import com.hkprogrammer.algafood.domain.models.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}
