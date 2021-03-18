/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component.table;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Invoice;

/**
 *
 * @author Katarina
 */
public class InvoiceTableModel extends AbstractTableModel{
    
    private final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    
    private List<Invoice> invoices;
    private final String[] columnNames = new String[]{
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.table.NUMBER"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.table.PARTNER"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.table.DATE"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.table.PROCESSED"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchInvoices.table.CANCELED")};
    private final Class[] columnClasses = new Class[]{String.class, String.class, String.class, Boolean.class, Boolean.class};

    public InvoiceTableModel(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice invoice = invoices.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return invoice.getNumber();
            case 1:
                return invoice.getPartner();
            case 2:
                return SDF.format(invoice.getDate());
            case 3:
                return invoice.isProcessed();
            case 4:
                return invoice.isCanceled();
            default:
                return "N/A";
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Invoice getInvoice(int row) {
        return invoices.get(row);
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        fireTableDataChanged();
    }
    
}
