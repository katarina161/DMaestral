/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component.table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Katarina
 */
public class InvoiceItemTableModel extends AbstractTableModel {
    
    private final String[] columnNames = new String[]{"Order No.", "Product", "Size", "Price", "Quantity"};
    private final Class[] columnClasses = new Class[]{Integer.class, Product.class, Integer.class, BigDecimal.class, Integer.class};
    private Invoice invoice;
    
    public InvoiceItemTableModel() {
        invoice = new Invoice();
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        return invoice.getItems().size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 4;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = invoice.getItems().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getOrderNumber();
            case 1:
                return item.getProduct();
            case 2:
                return item.getSize();
            case 3:
                return item.getPrice().setScale(2, RoundingMode.HALF_UP).doubleValue();
            case 4:
                return item.getQuantity();
            default:
                return "N/A";
        }
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 4) {
            InvoiceItem item = invoice.getItems().get(rowIndex);
            int quantity = (int) value;
            if (quantity > 0) {
                item.setQuantity(quantity);
                item.setTotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
                updateInvoiceTotalPrice();
            }
        }
        
    }
    
    public List<InvoiceItem> getItems() {
        return invoice.getItems();
    }
    
    public Invoice getInvoice() {
        return invoice;
    }
    
    public void addInvoiceItem(InvoiceItem itm) {
        InvoiceItem item = null;
        for (int i = 0; i < invoice.getItems().size(); i++) {
            if (invoice.getItems().get(i).getProduct().equals(itm.getProduct())
                    && invoice.getItems().get(i).getSize() == itm.getSize()) {
                item = invoice.getItems().get(i);
                item.setQuantity(itm.getQuantity() + invoice.getItems().get(i).getQuantity());
            }
        }
        if (item == null) {
            item = itm;
            item.setInvoice(invoice);
            item.setOrderNumber(invoice.getItems().size() + 1);
            invoice.getItems().add(item);
        }
        item.setTotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        updateInvoiceTotalPrice();
    }
    
    public void removeveInvoiceItem(int row) {
        invoice.getItems().remove(row);
        setOrderNumbers();
        updateInvoiceTotalPrice();
    }
    
    private void setOrderNumbers() {
        int num = 0;
        for (InvoiceItem item : invoice.getItems()) {
            item.setOrderNumber(++num);
        }
    }
    
    private void updateInvoiceTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceItem item : invoice.getItems()) {
            total = total.add(item.getTotal());
        }
        invoice.setTotal(total);
        fireTableDataChanged();
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
}
