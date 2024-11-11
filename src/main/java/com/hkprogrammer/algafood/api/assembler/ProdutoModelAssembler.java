package com.hkprogrammer.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.hkprogrammer.algafood.api.AlgaLink;
import com.hkprogrammer.algafood.api.controller.RestauranteProdutoController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.ProdutoModel;
import com.hkprogrammer.algafood.domain.models.Produto;

@Component
public class ProdutoModelAssembler
        extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLink algaLinks;

    public ProdutoModelAssembler() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }

    @Override
    public ProdutoModel toModel(Produto produto) {
        ProdutoModel produtoModel = createModelWithId(
                produto.getId(), produto, produto.getRestaurante().getId());

        modelMapper.map(produto, produtoModel);

        produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

        return produtoModel;
    }
}
