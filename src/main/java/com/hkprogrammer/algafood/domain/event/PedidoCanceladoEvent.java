package com.hkprogrammer.algafood.domain.event;

import com.hkprogrammer.algafood.domain.models.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;
    
}