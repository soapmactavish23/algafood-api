package com.hkprogrammer.algafood.domain.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Produto produto;

    private String nomeArquivo;

    private String descricao;

    private String contentType;

    private Long tamanho;
    
    public Long getRestauranteId() {
    	if(getProduto() != null) {
    		return getProduto().getRestaurante().getId();
    	}
    	return null;
    }
}
