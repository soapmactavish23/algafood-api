package com.hkprogrammer.algafood.api.v2.model.input;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInputV2 {

    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idCidade;

}
