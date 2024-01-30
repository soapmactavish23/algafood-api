package com.hkprogrammer.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class TestController {

	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping("/por-nome")
	public List<Cozinha> findByName(@RequestParam(defaultValue = "") String name) {
		return repository.consultarPorNome(name);
	}
	
}
