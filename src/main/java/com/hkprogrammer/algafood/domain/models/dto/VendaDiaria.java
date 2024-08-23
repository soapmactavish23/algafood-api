package com.hkprogrammer.algafood.domain.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class VendaDiaria {

    private LocalDateTime data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

}
