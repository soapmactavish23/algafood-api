package com.hkprogrammer.algafood.domain.service;

import com.hkprogrammer.algafood.core.security.CheckSecurity;
import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	public void confirmar(String codigo) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
		pedido.confirmar();		
		
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	public void cancelar(String codigo) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
	    pedido.cancelar();
	    
	    pedidoRepository.save(pedido);
	}

	@Transactional
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	public void entregar(String codigo) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
	    pedido.cancelar();
	}

}
