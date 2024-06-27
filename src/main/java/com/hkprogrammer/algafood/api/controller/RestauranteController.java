package com.hkprogrammer.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hkprogrammer.algafood.api.assembler.RestauranteInputDisassembler;
import com.hkprogrammer.algafood.api.assembler.RestauranteModelAssembler;
import com.hkprogrammer.algafood.api.model.RestauranteModel;
import com.hkprogrammer.algafood.api.model.input.RestauranteInput;
import com.hkprogrammer.algafood.api.model.view.RestauranteView;
import com.hkprogrammer.algafood.domain.exception.CidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.hkprogrammer.algafood.domain.exception.NegocioException;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.repository.RestauranteRepository;
import com.hkprogrammer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
		List<RestauranteModel> restauranteModel = restauranteModelAssembler.toCollectionModel(restaurantes);
		
		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteModel);
		
		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
		
		if("apenas-nome".equals(projecao)) {
			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
		} else if("completo".equals(projecao)) {
			restaurantesWrapper.setSerializationView(null);
		}
		
		return restaurantesWrapper;
	}

//	@GetMapping
//	public List<RestauranteModel> listarResumido() {
//		return listar();
//	}
//	
//	@GetMapping(params = "projecao=resumo")
//	@JsonView(RestauranteView.Resumo.class)
//	public List<RestauranteModel> listar() {
//		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//	}
//	
//	@GetMapping(params = "projecao=apenas-nome")
//	@JsonView(RestauranteView.ApenasNome.class)
//	public List<RestauranteModel> listarApenasNomes() {
//		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		RestauranteModel restauranteModel = restauranteModelAssembler.toModel(restaurante);

		return restauranteModel;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

		restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

		try {
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
	}

	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestaurante.ativar(restauranteIds);
	}
	
	@PutMapping("/desativacoes")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestaurante.inativar(restauranteIds);
	}
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
	    cadastroRestaurante.abrir(restauranteId);
	}

	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
	    cadastroRestaurante.fechar(restauranteId);
	}

}