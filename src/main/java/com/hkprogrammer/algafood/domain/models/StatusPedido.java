package com.hkprogrammer.algafood.domain.models;

public enum StatusPedido {
	CRIADO("Criado"), CONFIRMADO("Confirmado"), ENTREGUE("Entregue"), CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusPedido(String description) {
		this.descricao = description;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
