package com.hkprogrammer.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Setter
@Getter
@Relation(collectionRelation = "grupos")
public class GrupoModel extends RepresentationModel<GrupoModel> {

    private Long id;
    private String nome;

}