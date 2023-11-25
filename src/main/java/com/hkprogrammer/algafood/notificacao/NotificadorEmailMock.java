package com.hkprogrammer.algafood.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Profile("dev")
@Component
@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
public class NotificadorEmailMock implements Notificador {

	public void notificar(Cliente cliente, String message) {
		System.out.printf("Mock: Notificação seria enviada para %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), message);
	}
	
}
