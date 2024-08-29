package com.hkprogrammer.algafood.infraestructure.service.report;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        return null;
    }

}
