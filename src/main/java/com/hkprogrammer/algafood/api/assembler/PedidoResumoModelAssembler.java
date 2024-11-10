package com.hkprogrammer.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.hkprogrammer.algafood.api.AlgaLink;
import com.hkprogrammer.algafood.api.controller.PedidoController;
import com.hkprogrammer.algafood.api.controller.RestauranteController;
import com.hkprogrammer.algafood.api.controller.UsuarioController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.PedidoResumoModel;
import com.hkprogrammer.algafood.domain.models.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLink algaLinks;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		pedidoModel.add(algaLinks.linkToPedido());

		pedidoModel.getRestaurante().add(
				algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));

		return pedidoModel;
	}
    
}