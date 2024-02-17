package com.hkprogrammer.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.hkprogrammer.algafood.domain.models.Restaurante;

public interface RestauranteInterfaceQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

}