package com.hkprogrammer.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hkprogrammer.algafood.domain.models.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	public List<Restaurante> findByTaxaFreteBetween(BigDecimal tx1, BigDecimal tx2);
	
}

