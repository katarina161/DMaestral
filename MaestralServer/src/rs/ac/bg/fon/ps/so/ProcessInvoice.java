/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.domain.Invoice;

/**
 *
 * @author Katarina
 */
public class ProcessInvoice extends AbstractSystemOperation{
    
    private Invoice invoice;

    public ProcessInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (((Invoice)repository.getByPrimaryKey(new Invoice(), invoice.getId())).isProcessed()) {
            throw new Exception("Invoice (" +invoice.getNumber()+ ") is already processed!");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        invoice.setProcessed(true);
        repository.update(invoice);
    }

    public Invoice getInvoice() {
        return invoice;
    }
    
}
