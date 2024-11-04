package com.hkprogrammer.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.hkprogrammer.algafood.api.controller.CidadeController;
import com.hkprogrammer.algafood.api.controller.EstadoController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.CidadeModel;
import com.hkprogrammer.algafood.domain.models.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeModel.class);
    }

    public CidadeModel toModel(Cidade cidade) {
        CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map( cidade, cidadeModel );

        cidadeModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).buscar(cidadeModel.getId()))
                .withSelfRel());

        cidadeModel.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
                .withRel("listar"));

        cidadeModel.getEstado().add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId()))
                .withSelfRel());

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(CidadeController.class).withSelfRel());
    }

    //    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
//        return cidades.stream()
//                .map(cidade -> toModel(cidade))
//                .collect(Collectors.toList());
//    }
}