package com.hkprogrammer.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private EnvioEmailService envioEmail;

	@Transactional
	public void confirmar(String codigo) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		
		envioEmail.enviar(mensagem);
		
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
