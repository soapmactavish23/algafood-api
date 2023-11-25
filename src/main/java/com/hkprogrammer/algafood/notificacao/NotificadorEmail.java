package com.hkprogrammer.algafood.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;

@Profile("prod")
@Component
@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
public class NotificadorEmail implements Notificador {
	
	@Value("${notificador.email.host-servidor}")
	private String host;
	
	@Value("${notificador.email.porta-servidor}")
	private Integer porta;

	public void notificar(Cliente cliente, String message) {
		
		System.out.println(host);
		System.out.println(porta);
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), message);
	}
	
}
