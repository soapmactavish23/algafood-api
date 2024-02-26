package com.hkprogrammer.algafood.api.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problema {

	private LocalDateTime dataHora;
	private String mensagem;
	
}
