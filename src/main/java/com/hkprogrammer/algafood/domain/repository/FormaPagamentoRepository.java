package com.hkprogrammer.algafood.domain.repository;

import java.util.List;

import com.hkprogrammer.algafood.domain.models.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
	
}
