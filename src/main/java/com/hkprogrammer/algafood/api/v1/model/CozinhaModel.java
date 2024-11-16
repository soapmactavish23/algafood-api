package com.hkprogrammer.algafood.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "cozinhas")
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	//@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	//@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
