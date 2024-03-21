package com.hkprogrammer.algafood.domain.models.mixin;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.models.Endereco;
import com.hkprogrammer.algafood.domain.models.FormaPagamento;
import com.hkprogrammer.algafood.domain.models.Produto;

public abstract class RestauranteMixin {
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;
    
    @JsonIgnore
    private Endereco endereco;
    
    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAtualizacao;
    
    @JsonIgnore
    private List<FormaPagamento> formasPagamento;
    
    @JsonIgnore
    private List<Produto> produtos;
	
}
