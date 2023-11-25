package com.hkprogrammer.algafood.services;

import com.hkprogrammer.algafood.models.Cliente;
import com.hkprogrammer.algafood.notificacao.NotificadorEmail;

public class AtivacaoClienteService {

	private NotificadorEmail notificadorEmail;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificadorEmail.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
	
}
