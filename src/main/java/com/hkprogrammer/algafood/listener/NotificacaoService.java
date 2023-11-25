package com.hkprogrammer.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.notificacao.NivelUrgencia;
import com.hkprogrammer.algafood.notificacao.Notificador;
import com.hkprogrammer.algafood.notificacao.TipoNotificador;
import com.hkprogrammer.algafood.services.ClienteAtivadoEvent;

@Component
public class NotificacaoService {
	
	@Autowired
	@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
	private Notificador notificador;
	

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}
	
}
