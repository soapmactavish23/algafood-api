package com.hkprogrammer.algafood.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.models.Cliente;
import com.hkprogrammer.algafood.notificacao.NivelUrgencia;
import com.hkprogrammer.algafood.notificacao.Notificador;
import com.hkprogrammer.algafood.notificacao.TipoNotificador;

@Component
public class AtivacaoClienteService {
	
	@Autowired
	@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
	private Notificador notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

}