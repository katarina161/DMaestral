/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.util;

import java.util.Arrays;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import rs.ac.bg.fon.ps.domain.Invoice;

/**
 *
 * @author Katarina
 */
public class InvoiceReportGenerator {
    
    public static byte[] generateReport(Invoice invoice) throws JRException {
        byte[] report;
        
        HashMap params = new HashMap();
        params.put("ImagePath", "report/");
        params.put("items", new JRBeanCollectionDataSource(invoice.getItems()));
        
        JasperPrint jp = JasperFillManager.fillReport("report/InvoiceReport.jasper", params, new JRBeanCollectionDataSource(Arrays.asList(invoice)));
        report = JasperExportManager.exportReportToPdf(jp);
        
        return report;
    }
}
