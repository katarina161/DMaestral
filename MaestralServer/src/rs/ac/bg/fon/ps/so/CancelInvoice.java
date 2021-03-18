/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.math.BigDecimal;
import java.sql.Date;
import rs.ac.bg.fon.ps.domain.Invoice;

/**
 *
 * @author Katarina
 */
public class CancelInvoice extends AbstractSystemOperation{
    
    private Invoice invoice;

    public CancelInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (!((Invoice)repository.getByPrimaryKey(new Invoice(), invoice.getId())).isProcessed()) {
            throw new Exception(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("CancelInvoice.NOT_PROCESSED"));
        }
        if (((Invoice)repository.getByPrimaryKey(new Invoice(), invoice.getId())).isCanceled()) {
            throw new Exception(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("CancelInvoice.ALREADY_CANCELED"));
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        invoice.setCanceled(true);
        repository.update(invoice);
        BigDecimal total = invoice.getTotal().multiply(new BigDecimal(-1));
        invoice.setTotal(total);
        invoice.setDate(new Date(invoice.getDate().getTime()));
        repository.add(invoice);
    }
    
}
