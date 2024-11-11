package com.hkprogrammer.algafood.api.controller;

import java.util.List;

import com.hkprogrammer.algafood.api.AlgaLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.hkprogrammer.algafood.api.model.FormaPagamentoModel;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private AlgaLink algaLinks;

	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(restaurante.getFormasPagamento())
				.removeLinks()
				.add(algaLinks.linkToRestauranteFormasPagamento(restauranteId));

		formasPagamentoModel.getContent().forEach((formaPagamentoModel -> {
			formaPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
					restauranteId, formaPagamentoModel.getId(), "desassociar"));
		}));

		return formasPagamentoModel;
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}