package com.hkprogrammer.algafood.api.v2.model.input;

import com.hkprogrammer.algafood.api.v1.model.input.EstadoIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInputV2 {

    @NotBlank
    private String nome;

//    @Valid
//    @NotNull
//    private EstadoIdInput estado;

}