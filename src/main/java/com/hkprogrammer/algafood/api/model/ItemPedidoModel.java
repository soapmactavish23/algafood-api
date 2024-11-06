package com.hkprogrammer.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;            
}