/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Invoice;

/**
 *
 * @author Katarina
 */
public class GenerateInvoiceNumber extends AbstractSystemOperation {

    SimpleDateFormat SDF = new SimpleDateFormat("ddMMyyyy");
    private String number;

    public GenerateInvoiceNumber() {
        number = "INV-" + SDF.format(new Date());
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        int max = 0;
        List<Invoice> invoices = (List<Invoice>) repository.getAll(new Invoice());
        for (Invoice invoice : invoices) {
            String[] parts = invoice.getNumber().split("-");
            if (invoice.getNumber().contains(number)) {
                if (parts.length == 3) {
                    int add = Integer.parseInt(parts[2]);
                    if (add > max) {
                        max = add;
                    }
                }
            }
        }
        if (max == 0) {
            number += "-" + 1;
        } else {
            number += "-" + (max+1);
        }
    }

    public String getNumber() {
        return number;
    }

}
