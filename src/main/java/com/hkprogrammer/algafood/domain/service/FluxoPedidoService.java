package com.hkprogrammer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
		pedido.confirmar();		
		
		pedidoRepository.save(pedido);
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
