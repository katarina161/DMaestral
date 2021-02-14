/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbInvoice implements DbRepository<Invoice> {
    
    @Override
    public void add(Invoice invoice) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "INSERT INTO invoice (number, partner, date, total, user_id) VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, invoice.getNumber());
        ps.setString(2, invoice.getPartner());
        ps.setDate(3, new Date(invoice.getDate().getTime()));
        ps.setBigDecimal(4, invoice.getTotal());
        ps.setLong(5, invoice.getUser().getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1);
            invoice.setId(id);
            query = "INSERT INTO invoice_item (invoice_id, order_number, product_article, size_id, quantity, price, total) VALUES (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            for (InvoiceItem item : invoice.getItems()) {
                ps.setLong(1, invoice.getId());
                ps.setInt(2, item.getOrderNumber());
                ps.setLong(3, item.getProduct().getArticle());
                ps.setLong(4, item.getSize().getId());
                ps.setInt(5, item.getQuantity());
                ps.setBigDecimal(6, item.getPrice());
                ps.setBigDecimal(7, item.getTotal());
                ps.executeUpdate();
            }
        } else {
            throw new Exception("Invoice Id is not generated!");
        }
        
        rs.close();
        ps.close();
    }
    
    @Override
    public List<Invoice> getAll(Invoice param) throws Exception {
        List<Invoice> invoices = new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "SELECT i.id, i.number, i.partner, i.date, i.total, i.processed, i.canceled, "
                + "u.id, u.username, u.password, u.first_name, u.last_name, u.administrator FROM invoice i "
                + "JOIN user u ON i.user_id = u.id WHERE i.total > 0";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {            
            User u = new User();
            u.setId(rs.getLong("u.id"));
            u.setUsername(rs.getString("u.username"));
            u.setPassword(rs.getString("u.password"));
            u.setFirstName(rs.getString("u.first_name"));
            u.setLastName(rs.getString("u.last_name"));
            u.setAdministrator(rs.getBoolean("u.administrator"));
            
            Invoice invoice = new Invoice();
            invoice.setId(rs.getLong("i.id"));
            invoice.setNumber(rs.getString("i.number"));
            invoice.setPartner(rs.getString("i.partner"));
            invoice.setDate(new java.util.Date(rs.getDate("i.date").getTime()));
            invoice.setTotal(rs.getBigDecimal("i.total"));
            invoice.setProcessed(rs.getBoolean("i.processed"));
            invoice.setCanceld(rs.getBoolean("i.canceled"));
            invoice.setUser(u);
            
            invoices.add(invoice);
        }
        
        query = "SELECT p.article, p.name, p.description, p.price, p.price_with_vat, "
                + "s.id, s.number, "
                + "i.order_number, i.quantity, i.price, i.total FROM invoice_item i "
                + "JOIN product p ON i.product_article = p.article "
                + "JOIN size s ON s.id = i.size_id "
                + "WHERE i.invoice_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        for (Invoice invoice : invoices) {
            ps.setLong(1, invoice.getId());
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                Product p = new Product();
                p.setArticle(rs.getLong("p.article"));
                p.setName(rs.getString("p.name"));
                p.setDescription(rs.getString("p.description"));
                p.setCategory(getProductCategory(p));
                p.setPriceWithoutVAT(rs.getBigDecimal("p.price"));
                p.setPriceWithVAT(rs.getBigDecimal("p.price_with_vat"));
                p.setSizes(getProductSizes(p));
                
                Size s = new Size();
                s.setId(rs.getLong("s.id"));
                s.setSizeNumber(rs.getInt("s.number"));
                
                InvoiceItem item = new InvoiceItem();
                item.setInvoice(invoice);
                item.setOrderNumber(rs.getInt("i.order_number"));
                item.setQuantity(rs.getInt("i.quantity"));
                item.setPrice(rs.getBigDecimal("i.price"));
                item.setTotal(rs.getBigDecimal("i.total"));
                item.setProduct(p);
                item.setSize(s);
                
                invoice.getItems().add(item);                
            }
            
        }
        
        rs.close();
        statement.close();
        ps.close();
        
        return invoices;
    }
    
    private List<Size> getProductSizes(Product product) throws Exception {
        List<Size> sizes = new ArrayList<>();
        
        String query = "SELECT s.id, s.number "
                + "FROM product_size p JOIN size s ON p.size_id = s.id "
                + "WHERE p.product_article = ?";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, product.getArticle());
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Size s = new Size();
            s.setId(rs.getLong("s.id"));
            s.setSizeNumber(rs.getInt("s.number"));
            sizes.add(s);
        }
        rs.close();
        ps.close();
        
        return sizes;
    }
    
    @Override
    public void update(Invoice param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Invoice param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Category getProductCategory(Product product) throws Exception {
        Category c = new Category();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "SELECT c.id, c.name FROM product p "
                + "JOIN category c ON p.category_id = c.id WHERE p.article = " + product.getArticle();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {            
            c.setId(rs.getLong("c.id"));
            c.setName(rs.getString("c.name"));
        }
        
        rs.close();
        statement.close();
        
        return c;
    }

    @Override
    public List<Invoice> getAll(Invoice param, List<String> columns, List<Object> values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invoice getByPrimaryKey(Invoice param, Long primaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
