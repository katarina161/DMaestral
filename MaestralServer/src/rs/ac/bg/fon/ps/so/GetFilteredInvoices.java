/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public class GetFilteredInvoices extends AbstractSystemOperation{
    
    private final List<String> columns;
    private final List<Object> values;
    private List<Invoice> invoices;

    public GetFilteredInvoices(List<String> columns, List<Object> values) {
        this.columns = columns;
        this.values = values;
        invoices = new ArrayList<>();
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        List<Invoice> dbInvoices = (List<Invoice>) repository.getAll(new Invoice(), columns, values);
        dbInvoices.forEach(invoice -> {
            if (invoice.getTotal().compareTo(BigDecimal.ZERO) == 1) {
                invoices.add(invoice);
            }
        });
        for (Invoice i : invoices) {
            List<InvoiceItem> items
                    = (List<InvoiceItem>) repository.getAll(new InvoiceItem(), Arrays.asList("invoice_id"), Arrays.asList(i.getId()));
            for (InvoiceItem item: items) {
                Product p = (Product) repository.getByPrimaryKey(new Product(), item.getProduct().getArticle());
                item.setProduct(p);
            }
            i.setItems(items);
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
    
}
