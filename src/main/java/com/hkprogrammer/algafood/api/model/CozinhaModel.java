package com.hkprogrammer.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.hkprogrammer.algafood.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
