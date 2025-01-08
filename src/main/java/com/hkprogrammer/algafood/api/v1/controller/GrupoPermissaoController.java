package com.hkprogrammer.algafood.api.v1.controller;

import com.hkprogrammer.algafood.api.v1.AlgaLink;
import com.hkprogrammer.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.hkprogrammer.algafood.api.v1.model.PermissaoModel;
import com.hkprogrammer.algafood.core.security.CheckSecurity;
import com.hkprogrammer.algafood.domain.models.Grupo;
import com.hkprogrammer.algafood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@Autowired
	private AlgaLink algaLinks;

	@GetMapping
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		CollectionModel<PermissaoModel> permissoesModel
				= permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
				.removeLinks()
				.add(algaLinks.linkToGrupoPermissoes(grupoId))
				.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

		permissoesModel.getContent().forEach(permissaoModel -> {
			permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
					grupoId, permissaoModel.getId(), "desassociar"));
		});

		return permissoesModel;
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

}
