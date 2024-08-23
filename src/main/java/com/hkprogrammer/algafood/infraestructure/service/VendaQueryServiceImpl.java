package com.hkprogrammer.algafood.infraestructure.service;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.models.dto.VendaDiaria;
import com.hkprogrammer.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaQueryServiceImpl implements VendaQueryService {
    @Override
    public List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro) {
        return List.of();
    }
}
