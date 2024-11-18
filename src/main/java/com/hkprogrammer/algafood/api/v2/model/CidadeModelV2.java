package com.hkprogrammer.algafood.api.v2.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Setter
@Getter
@Relation(collectionRelation = "cidades")
public class CidadeModelV2 extends RepresentationModel<com.hkprogrammer.algafood.api.v1.model.CidadeModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Uberlândia")
    private String nome;

    private Long idEstado;

    private String nomeEstado;

}
