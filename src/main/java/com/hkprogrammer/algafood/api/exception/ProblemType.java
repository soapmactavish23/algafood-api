package com.hkprogrammer.algafood.api.exception;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_NAO_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ACESSO_NEGADO("/acesso-negado", "Acesso negado");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br/" + path;
		this.title = title;
	}
	
	
	
}
