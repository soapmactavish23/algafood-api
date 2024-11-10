package com.hkprogrammer.algafood.domain.models;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	CRIADO("Criado"), CONFIRMADO("Confirmado", CRIADO), ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;

	StatusPedido(String description, StatusPedido... statusAnteriores) {
		this.descricao = description;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}

	public String getDescricao() {
		return this.descricao;
	}

	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}

	public boolean podePodeAlterarPara(StatusPedido novoStatus) {
		return !naoPodeAlterarPara(novoStatus);
	}
	
}
