package com.hkprogrammer.algafood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "cidades")
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel> {

    private Long id;
    private String nome;
    private String estado;

}
