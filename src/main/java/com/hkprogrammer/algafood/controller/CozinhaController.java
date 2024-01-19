package com.hkprogrammer.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.models.Cozinha;
import com.hkprogrammer.algafood.models.CozinhaXmlWrapper;
import com.hkprogrammer.algafood.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_PROBLEM_XML_VALUE)
	public List<Cozinha> listar() {
		return repository.findAll();
	}

//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhaXmlWrapper listarXML() {
//		CozinhaXmlWrapper obj = new CozinhaXmlWrapper(repository.findAll());
//		System.out.println(obj);
//		return obj;
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = repository.findById(id);
		return ResponseEntity.ok(cozinha.get());
	}

}
