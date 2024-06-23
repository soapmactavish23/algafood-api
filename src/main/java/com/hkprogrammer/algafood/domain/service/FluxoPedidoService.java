package com.hkprogrammer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.models.Pedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(String codigo) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
	    pedido.cancelar();
	}

	@Transactional
	public void entregar(String codigo) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
	    pedido.cancelar();
	}

}
