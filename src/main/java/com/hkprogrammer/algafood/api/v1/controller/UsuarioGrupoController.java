package com.hkprogrammer.algafood.api.v1.controller;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.assembler.GrupoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.GrupoModel;
import com.hkprogrammer.algafood.core.security.CheckSecurity;
import com.hkprogrammer.algafood.domain.models.Usuario;
import com.hkprogrammer.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private AlgaLink algaLinks;

    @GetMapping
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

        CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        gruposModel.getContent().forEach(grupoModel -> {
            grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoModel.getId(), "desassociar"));
        });

        return gruposModel;
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.desassociarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuario.associarGrupo(usuarioId, grupoId);

        return ResponseEntity.noContent().build();
    }
}