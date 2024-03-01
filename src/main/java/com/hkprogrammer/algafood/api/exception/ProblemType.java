package com.hkprogrammer.algafood.api.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_NAO_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");       
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br/" + path;
		this.title = title;
	}
	
	
	
}
