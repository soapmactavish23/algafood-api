package com.hkprogrammer.algafood.api.v1.assembler;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.controller.PedidoController;
import com.hkprogrammer.algafood.api.v1.model.PedidoResumoModel;
import com.hkprogrammer.algafood.core.security.AlgaSecurity;
import com.hkprogrammer.algafood.domain.models.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLink algaLinks;

	@Autowired
	private AlgaSecurity algaSecurity;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		if (algaSecurity.podePesquisarPedidos()) {
			pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		}

		if (algaSecurity.podeConsultarRestaurantes()) {
			pedidoModel.getRestaurante().add(
					algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		}

		if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
		}

		return pedidoModel;
	}
    
}