package com.hkprogrammer.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteInterfaceQueries {

	public List<Restaurante> findByTaxaFreteBetween(BigDecimal tx1, BigDecimal tx2);
	
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	public Optional<Restaurante> findFirstRestauranteByNome(String name);
	
}

