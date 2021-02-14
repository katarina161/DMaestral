/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;

/**
 *
 * @author Katarina
 */
public class DeleteInvoice extends AbstractSystemOperation{
    
    private Invoice invoice;

    public DeleteInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (repository.getByPrimaryKey(new Invoice(), invoice.getId()) == null) {
            throw new Exception("This invoice is already deleted!");
        }
        if (invoice.isProcessed()) {
            throw new Exception("You cannot delete an invoice if it's already processed.\nYou can only cancel it.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        for (InvoiceItem item: invoice.getItems()) {
            repository.delete(item);
        }
        repository.delete(invoice);
    }
    
}
