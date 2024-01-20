package com.hkprogrammer.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService service;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			Restaurante obj = service.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
