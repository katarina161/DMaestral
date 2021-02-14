/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.Arrays;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;

/**
 *
 * @author Katarina
 */
public class UpdateInvoice extends AbstractSystemOperation{
    
    private Invoice invoice;

    public UpdateInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        List<InvoiceItem> dbItems 
                = (List<InvoiceItem>) repository.getAll(new InvoiceItem(), Arrays.asList("invoice_id"), Arrays.asList(invoice.getId()));
        for (InvoiceItem item: dbItems) {
            repository.delete(item);
        }
        
        repository.update(invoice);
        for (InvoiceItem item: invoice.getItems()) {
            repository.add(item);
        }
    }
    
}
