package com.hkprogrammer.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.hkprogrammer.algafood.api.model.input.RestauranteInput;
import com.hkprogrammer.algafood.domain.models.Cozinha;
import com.hkprogrammer.algafood.domain.models.Restaurante;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());
        
        restaurante.setCozinha(cozinha);
        
        return restaurante;
    }
    
}