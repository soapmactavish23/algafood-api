package com.hkprogrammer.algafood.domain.models.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hkprogrammer.algafood.domain.models.Estado;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
    
}    
