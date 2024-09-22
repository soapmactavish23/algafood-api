package com.hkprogrammer.algafood.domain.service;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

public interface EnvioEmailService {

	void enviar(Message message);
	
	@Getter
	@Builder
	class Message {
		private Set<String> destinatarios;
		private String assunto;
		private String corpo;
	}
	
}
