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
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Katarina
 */
public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.table.ARTICLE"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.table.NAME"), 
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.table.CATEGORY"),
        java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmSearchProducts.table.PRICE")};
    private final Class[] columnClasses = new Class[]{Long.class, String.class, Category.class, BigDecimal.class};
    private List<Product> products;

    public ProductTableModel(List<Product> products) {
        this.products = products;
    }

    @Override
    public String getColumnName(int column) {
        if (column >= columnNames.length || column < 0) {
            return "N/A";
        }
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= columnClasses.length || columnIndex < 0) {
            return Object.class;
        }
        return columnClasses[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return product.getArticle();
            case 1:
                return product.getName();
            case 2:
                return product.getCategory();
            case 3:
                return product.getPriceWithVAT().setScale(2, RoundingMode.HALF_UP).doubleValue();
            default:
                return "N/A";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return columnIndex == 1 || columnIndex == 2;
        return false;
    }

//    @Override
//    public void setValueAt(Object value, int rowIndex, int columnIndex) {
//        Product product = products.get(rowIndex);
//        switch (columnIndex) {
//            case 1:
//                product.setName(value.toString());
//                break;
//            case 2:
//                product.setCategory((Category)value);
//                break;
//        }
//    }

    public void deleteProduct(int selectedRow) {
        products.remove(selectedRow);
        fireTableDataChanged();
    }
    
    public Product getProduct(int selectedRow) {
        return products.get(selectedRow);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        fireTableDataChanged();
    }
    
}
