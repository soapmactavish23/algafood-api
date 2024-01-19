package com.hkprogrammer.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.models.Cozinha;
import com.hkprogrammer.algafood.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;

	@GetMapping
	public ResponseEntity<List<Cozinha>> listar() {
		List<Cozinha> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = repository.findById(id);
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Cozinha> adicionarCozinha(@RequestBody Cozinha cozinha) {
		Cozinha objSaved = repository.save(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(objSaved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@RequestBody Cozinha obj, @PathVariable Long id) {
		Cozinha objSaved = buscar(id).getBody();
		BeanUtils.copyProperties(obj, objSaved, "id");
		return ResponseEntity.ok(objSaved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> delete(@PathVariable Long id) {
		try {
			Cozinha cozinha = repository.buscar(id);

			if (cozinha != null) {
				repository.deleteById(id);
				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
