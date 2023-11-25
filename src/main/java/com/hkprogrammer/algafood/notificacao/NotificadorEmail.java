package com.hkprogrammer.algafood.notificacao;

import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Component
public class NotificadorEmail {

	public void notificar(Cliente cliente, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), message);
	}
	
}
