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
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;

/**
 *
 * @author Katarina
 */
public class GetAllInvoices extends AbstractSystemOperation {

    private List<Invoice> invoices;

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        invoices = (List<Invoice>) repository.getAll(new Invoice());
        for (Invoice i : invoices) {
            List<InvoiceItem> items
                    = (List<InvoiceItem>) repository.getAll(new InvoiceItem(), Arrays.asList("invoice_id"), Arrays.asList(i.getId()));
            for (InvoiceItem item: items) {
                Product p = (Product) repository.getByPrimaryKey(new Product(), item.getProduct().getArticle());
                Size s = (Size) repository.getByPrimaryKey(new Size(), item.getSize().getId());
                item.setProduct(p);
                item.setSize(s);
            }
            i.setItems(items);
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

}
