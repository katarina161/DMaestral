/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.Arrays;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;

/**
 *
 * @author Katarina
 */
public class AddInvoice extends AbstractSystemOperation {

    private Invoice invoice;

    public AddInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (!repository.getAll(new Invoice(), Arrays.asList("number"), Arrays.asList(invoice.getNumber())).isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("AddInvoice.ERROR_1"))
                    .append(invoice.getNumber())
                    .append(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("AddInvoice.ERROR_2"));
            throw new Exception(sb.toString());
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        repository.add(invoice);
        for (InvoiceItem item: invoice.getItems()) {
            repository.add(item);
        }
    }

}
