package com.hkprogrammer.algafood.api.controller;

import com.hkprogrammer.algafood.api.AlgaLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLink algaLink;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLink.linkToCozinhas("cozinhas"));
        rootEntryPointModel.add(algaLink.linkToPedidos("pedidos"));
        rootEntryPointModel.add(algaLink.linkToRestaurantes("restaurantes"));
        rootEntryPointModel.add(algaLink.linkToGrupos("grupos"));
        rootEntryPointModel.add(algaLink.linkToUsuarios("usuarios"));
        rootEntryPointModel.add(algaLink.linkToPermissoes("permissoes"));
        rootEntryPointModel.add(algaLink.linkToFormasPagamento("formasPagamento"));
        rootEntryPointModel.add(algaLink.linkToEstados("estados"));
        rootEntryPointModel.add(algaLink.linkToCidades("cidades"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}

}
