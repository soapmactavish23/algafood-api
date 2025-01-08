package com.hkprogrammer.algafood.api.v1.controller;

import com.hkprogrammer.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.hkprogrammer.algafood.api.v1.assembler.GrupoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.GrupoModel;
import com.hkprogrammer.algafood.api.v1.model.input.GrupoInput;
import com.hkprogrammer.algafood.core.security.CheckSecurity;
import com.hkprogrammer.algafood.domain.models.Grupo;
import com.hkprogrammer.algafood.domain.repository.GrupoRepository;
import com.hkprogrammer.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    public CollectionModel<GrupoModel> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = cadastroGrupo.salvar(grupo);

        return grupoModelAssembler.toModel(grupo);
    }

    @PutMapping("/{grupoId}")
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    public GrupoModel atualizar(@PathVariable Long grupoId,
            @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupo.salvar(grupoAtual);

        return grupoModelAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupo.excluir(grupoId);
    }
}