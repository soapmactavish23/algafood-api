package com.hkprogrammer.algafood.api.v1.controller;

import javax.validation.Valid;

import com.hkprogrammer.algafood.core.data.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.hkprogrammer.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.hkprogrammer.algafood.api.v1.assembler.PedidoModelAssembler;
import com.hkprogrammer.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.PedidoModel;
import com.hkprogrammer.algafood.api.v1.model.PedidoResumoModel;
import com.hkprogrammer.algafood.api.v1.model.input.PedidoInput;
import com.hkprogrammer.algafood.core.data.PageableTranslator;
import com.hkprogrammer.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.hkprogrammer.algafood.domain.exception.NegocioException;
import com.hkprogrammer.algafood.domain.models.Pedido;
import com.hkprogrammer.algafood.domain.models.Usuario;
import com.hkprogrammer.algafood.domain.repository.PedidoRepository;
import com.hkprogrammer.algafood.domain.filter.PedidoFilter;
import com.hkprogrammer.algafood.domain.service.EmissaoPedidoService;
import com.hkprogrammer.algafood.infraestructure.repository.spec.PedidoSpecs;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/pedidos")
@Slf4j
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
												   @PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);

		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = ImmutableMap.of(
					"codigo", "codigo",
					"restaurante.nome", "restaurante.nome",
					"nomeCliente", "cliente.nome",
					"valorTotal", "valorTotal"
				);
		return PageableTranslator.translate(pageable, mapeamento);
	}
}