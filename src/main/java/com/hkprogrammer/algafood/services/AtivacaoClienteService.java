package com.hkprogrammer.algafood.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.hkprogrammer.algafood.models.Cliente;
import com.hkprogrammer.algafood.notificacao.NivelUrgencia;
import com.hkprogrammer.algafood.notificacao.Notificador;
import com.hkprogrammer.algafood.notificacao.TipoNotificador;

//@Component
public class AtivacaoClienteService {
	
	@Autowired
	@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
	private Notificador notificador;
	
//	@PostConstruct
	public void init() {
		System.out.println("INIT");
	}
	
//	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

}