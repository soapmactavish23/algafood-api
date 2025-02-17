package com.hkprogrammer.algafood.domain.repository;

import com.hkprogrammer.algafood.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteInterfaceQueries, 
							JpaSpecificationExecutor<Restaurante> {

	public List<Restaurante> findByTaxaFreteBetween(BigDecimal tx1, BigDecimal tx2);
	
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	public Optional<Restaurante> findFirstRestauranteByNome(String name);

	boolean existsResponsavel(Long restauranteId, Long usuarioId);
}

