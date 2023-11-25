package com.hkprogrammer.algafood.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Qualifier("email")
@Component
public class NotificadorEmail implements Notificador {

	public void notificar(Cliente cliente, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), message);
	}
	
}
