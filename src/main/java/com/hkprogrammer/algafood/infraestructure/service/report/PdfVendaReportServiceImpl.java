package com.hkprogrammer.algafood.infraestructure.service.report;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.service.VendaQueryService;
import com.hkprogrammer.algafood.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        return null;
//        try {
//            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
//
//            var parametros = new HashMap<String, Object>();
//            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
//
//            var vendasDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
//            var dataSource = new JRBeanCollectionDataSource(vendasDiarias);
//
//            var jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
//
//            return JasperExportManager.exportReportToPdf(jasperPrint);
//        } catch (JRException e) {
//            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
//        }

    }

}
