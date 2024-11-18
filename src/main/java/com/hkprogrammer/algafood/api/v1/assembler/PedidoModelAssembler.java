package com.hkprogrammer.algafood.api.v1.assembler;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.controller.PedidoController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.v1.model.PedidoModel;
import com.hkprogrammer.algafood.domain.models.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLink algaLinks;

    @Autowired
    private AlgaLink algaLink;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(algaLinks.linkToPedido());

        if(pedido.podeSerConfirmado()) {
            pedidoModel.add(algaLink.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
        }

        if(pedido.podeSerCancelado()) {
            pedidoModel.add(algaLink.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
        }

        if(pedido.podeSerEntregue()) {
            pedidoModel.add(algaLink.linkToEntregarPedido(pedido.getCodigo(), "entregar"));
        }

        pedidoModel.getRestaurante().add(
                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(
                algaLinks.linkToUsuario(pedido.getCliente().getId()));

        pedidoModel.getFormaPagamento().add(
                algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

        pedidoModel.getEnderecoEntrega().getCidade().add(
                algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

        pedidoModel.getItens().forEach(item -> {
            item.add(algaLinks.linkToProduto(
                    pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });

        return pedidoModel;
    }
    
}