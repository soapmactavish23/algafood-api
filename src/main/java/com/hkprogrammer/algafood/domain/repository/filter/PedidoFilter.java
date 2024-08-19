package com.hkprogrammer.algafood.domain.repository.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonFilter("pedidoFilter")
public class PedidoFilter {

	private Long clienteId;
	private Long restauranteId;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	
}