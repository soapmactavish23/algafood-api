package com.hkprogrammer.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.hkprogrammer.algafood.domain.models.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}