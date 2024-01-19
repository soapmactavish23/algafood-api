package com.hkprogrammer.algafood.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.models.Estado;
import com.hkprogrammer.algafood.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository repository;
	
	@GetMapping()
	public List<Estado> findAll() {
		return repository.findAll();
	}
}
