/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.math.BigDecimal;
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
            throw new Exception("You cannot cancel an invoice that is not processed.");
        }
        if (((Invoice)repository.getByPrimaryKey(new Invoice(), invoice.getId())).isCanceld()) {
            throw new Exception("This invoice is already cancel.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        invoice.setCanceld(true);
        repository.update(invoice);
        BigDecimal total = invoice.getTotal().multiply(new BigDecimal(-1));
        invoice.setTotal(total);
        repository.add(invoice);
    }
    
}
