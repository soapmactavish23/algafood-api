package com.hkprogrammer.algafood.domain.service;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.models.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
