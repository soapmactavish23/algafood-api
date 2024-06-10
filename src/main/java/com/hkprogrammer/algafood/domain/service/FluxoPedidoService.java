package com.hkprogrammer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.exception.NegocioException;
import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.models.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(Long pedidoId) {

		Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO));
		}

	}

}
