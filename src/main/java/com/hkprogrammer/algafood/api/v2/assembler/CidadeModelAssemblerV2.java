package com.hkprogrammer.algafood.api.v2.assembler;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.controller.CidadeController;
import com.hkprogrammer.algafood.api.v2.controller.CidadeControllerV2;
import com.hkprogrammer.algafood.api.v2.model.CidadeModelV2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.v1.model.CidadeModel;
import com.hkprogrammer.algafood.domain.models.Cidade;

@Component("cidadeModelAssemblerV2")
public class CidadeModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLink algaLinks;

    public CidadeModelAssemblerV2() {
        super(CidadeControllerV2.class, CidadeModelV2.class);
    }

    @Override
    public CidadeModelV2 toModel(Cidade cidade) {
        CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

        cidadeModel.add(algaLinks.linkToCidades("cidades"));

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToCidades());
    }
}