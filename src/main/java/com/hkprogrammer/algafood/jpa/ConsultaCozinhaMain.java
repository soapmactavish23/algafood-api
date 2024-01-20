package com.hkprogrammer.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.hkprogrammer.algafood.AlgafoodApiApplication;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		List<Cozinha> todasCozinhas = cozinhaRepository.listar();
		
		for (Cozinha cozinha : todasCozinhas) {
			System.out.println(cozinha.getNome());
		}
	}
	
}