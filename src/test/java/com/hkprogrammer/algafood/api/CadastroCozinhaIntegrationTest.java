package com.hkprogrammer.algafood.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
public class CadastroCozinhaIntegrationTest {

	@Autowired
	private CadastroCozinhaService service;
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Cozinha Teste");
		
		novaCozinha = service.salvar(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();	
	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {
		
	}
	
}
