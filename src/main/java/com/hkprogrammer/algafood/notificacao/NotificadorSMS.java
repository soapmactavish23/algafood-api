package com.hkprogrammer.algafood.notificacao;

import com.hkprogrammer.algafood.models.Cliente;

public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificador %s por SMS através do telefone %s: $s\n", cliente.getNome(), cliente.getTelefone(), mensagem);
		
	}

}
