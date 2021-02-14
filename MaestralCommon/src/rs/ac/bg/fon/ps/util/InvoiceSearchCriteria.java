/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Katarina
 */
public class InvoiceSearchCriteria implements Serializable{
    
    private String number;
    private String partner;
    private Date date;
    private boolean processed;
    private boolean canceled;

    public InvoiceSearchCriteria() {
    }

    public InvoiceSearchCriteria(String number, String partner, Date date) {
        this.number = number;
        this.partner = partner;
        this.date = date;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
}
