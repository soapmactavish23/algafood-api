package com.hkprogrammer.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.hkprogrammer.algafood.api.v1.assembler.EstadoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.EstadoModel;
import com.hkprogrammer.algafood.api.v1.model.input.EstadoInput;
import com.hkprogrammer.algafood.domain.models.Estado;
import com.hkprogrammer.algafood.domain.repository.EstadoRepository;
import com.hkprogrammer.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@GetMapping
	public CollectionModel<EstadoModel> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();

		return estadoModelAssembler.toCollectionModel(todosEstados);
	}


	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

		return estadoModelAssembler.toModel(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

		estado = cadastroEstado.salvar(estado);

		return estadoModelAssembler.toModel(estado);
	}

	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId,
			@RequestBody @Valid EstadoInput estadoInput) {
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		estadoAtual = cadastroEstado.salvar(estadoAtual);

		return estadoModelAssembler.toModel(estadoAtual);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}

}