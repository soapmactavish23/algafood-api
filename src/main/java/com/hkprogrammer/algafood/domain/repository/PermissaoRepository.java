package com.hkprogrammer.algafood.domain.repository;

import java.util.List;

import com.hkprogrammer.algafood.domain.models.Permissao;

public interface PermissaoRepository {

	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
	
}
