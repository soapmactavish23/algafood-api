package com.hkprogrammer.algafood.notificacao;

import com.hkprogrammer.algafood.models.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);
	
}
