package com.hkprogrammer.algafood.domain.service;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
