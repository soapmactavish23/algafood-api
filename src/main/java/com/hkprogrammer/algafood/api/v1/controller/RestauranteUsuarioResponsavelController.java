package com.hkprogrammer.algafood.api.v1.controller;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.UsuarioModel;
import com.hkprogrammer.algafood.core.security.AlgaSecurity;
import com.hkprogrammer.algafood.core.security.CheckSecurity;
import com.hkprogrammer.algafood.domain.models.Restaurante;
import com.hkprogrammer.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private AlgaLink algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
                .toCollectionModel(restaurante.getResponsaveis())
                .removeLinks();

        usuariosModel.add(algaLinks.linkToRestauranteResponsaveis(restauranteId));

        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            usuariosModel.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

            usuariosModel.getContent().stream().forEach(usuarioModel -> {
                usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                        restauranteId, usuarioModel.getId(), "desassociar"));
            });
        }

        return usuariosModel;
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }
}