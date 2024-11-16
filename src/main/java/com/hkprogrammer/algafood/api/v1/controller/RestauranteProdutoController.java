package com.hkprogrammer.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hkprogrammer.algafood.api.v1.assembler.ProdutoInputDisassembler;
import com.hkprogrammer.algafood.api.v1.assembler.ProdutoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.ProdutoModel;
import com.hkprogrammer.algafood.api.v1.model.input.ProdutoInput;
import com.hkprogrammer.algafood.domain.models.Produto;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.repository.ProdutoRepository;
import com.hkprogrammer.algafood.domain.service.CadastroProdutoService;
import com.hkprogrammer.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;

	@Autowired
	private AlgaLink algaLinks;

	@GetMapping
	public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
												@RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		List<Produto> todosProdutos = null;

		if (incluirInativos) {
			todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivoByRestaurante(restaurante);
		}

		return produtoModelAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToProdutos(restauranteId));
	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);

		produto = cadastroProduto.salvar(produto);

		return produtoModelAssembler.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestBody @Valid ProdutoInput produtoInput) {
		Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);

		produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

		produtoAtual = cadastroProduto.salvar(produtoAtual);

		return produtoModelAssembler.toModel(produtoAtual);
	}
}