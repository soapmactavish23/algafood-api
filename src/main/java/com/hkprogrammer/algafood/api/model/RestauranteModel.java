package com.hkprogrammer.algafood.api.model;

import java.math.BigDecimal;

import com.hkprogrammer.algafood.domain.models.Cozinha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private Cozinha cozinha;
	
}
