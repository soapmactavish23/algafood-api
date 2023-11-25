package com.hkprogrammer.algafood.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Profile("prod")
@Component
@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
public class NotificadorEmail implements Notificador {

	public void notificar(Cliente cliente, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), message);
	}
	
}
