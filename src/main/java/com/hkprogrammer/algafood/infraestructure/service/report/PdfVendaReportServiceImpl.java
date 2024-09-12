package com.hkprogrammer.algafood.infraestructure.service.report;

import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.domain.filter.VendaDiariaFilter;
import com.hkprogrammer.algafood.domain.service.VendaReportService;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

//    @Autowired	
//    private VendaQueryService vendaQueryService;

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
