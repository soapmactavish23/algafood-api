package com.hkprogrammer.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;
import com.hkprogrammer.algafood.domain.repository.RestauranteRepository;
import com.hkprogrammer.algafood.infraestructure.repository.spec.RestauranteComFreteGratisSpec;
import com.hkprogrammer.algafood.infraestructure.repository.spec.RestauranteComNomeSemelhanteSpec;

@RestController
@RequestMapping("/teste")
public class TestController {

	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/por-nome")
	public List<Cozinha> findByName(@RequestParam(defaultValue = "") String name) {
		return repository.findByNomeContaining(name);
	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> findRestauranteByName(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return restauranteRepository.findAll(comNomeSemelhante.and(comFreteGratis));
	}

}
