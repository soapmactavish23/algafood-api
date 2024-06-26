package com.hkprogrammer.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

	Optional<Pedido> findByCodigo(String codigo);
	
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante")
	public List<Pedido> findAll();
	
}