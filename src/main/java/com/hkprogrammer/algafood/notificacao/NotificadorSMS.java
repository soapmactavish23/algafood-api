package com.hkprogrammer.algafood.notificacao;

import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Component
public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificador %s por SMS através do telefone %s: $s\n", cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}
