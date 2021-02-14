/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbProduct implements DbRepository<Product> {

    @Override
    public void add(Product product) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "INSERT INTO product (article, name, category_id, description, price, price_with_vat) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, product.getArticle());
        ps.setString(2, product.getName());
        ps.setLong(3, product.getCategory().getId());
        ps.setString(4, product.getDescription());
        ps.setBigDecimal(5, product.getPriceWithoutVAT());
        ps.setBigDecimal(6, product.getPriceWithVAT());
        ps.executeUpdate();

        query = "INSERT INTO product_size (product_article, size_id) VALUES (?,?)";
        ps = connection.prepareStatement(query);
        ps.setLong(1, product.getArticle());
        for (Size s : product.getSizes()) {
            ps.setLong(2, s.getId());
            ps.executeUpdate();
        }

        ps.close();
    }

    @Override
    public List<Product> getAll(Product param) throws Exception {
        List<Product> products = new ArrayList<>();

        String query = "SELECT p.article, p.name, p.description, p.price, p.price_with_vat, c.id, c.name"
                + " FROM product p JOIN category c ON p.category_id = c.id";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getLong("c.id"));
            category.setName(rs.getString("c.name"));

            Product product = new Product();
            product.setArticle(rs.getLong("p.article"));
            product.setName(rs.getString("p.name"));
            product.setDescription(rs.getString("p.description"));
            product.setCategory(category);
            product.setPriceWithoutVAT(new BigDecimal(rs.getDouble("p.price")));
            product.setPriceWithVAT(new BigDecimal(rs.getDouble("p.price_with_vat")));
            product.setSizes(getProductSizes(product));
            products.add(product);
        }
        rs.close();
        statement.close();

        return products;
    }

    @Override
    public void update(Product param) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "UPDATE product SET name=?, description=?, category_id=?, price=?, price_with_vat=? WHERE article=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, param.getName());
        ps.setString(2, param.getDescription());
        ps.setLong(3, param.getCategory().getId());
        ps.setBigDecimal(4, param.getPriceWithoutVAT());
        ps.setBigDecimal(5, param.getPriceWithVAT());
        ps.setLong(6, param.getArticle());

        updateProductSizes(param);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(Product param) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "DELETE FROM product WHERE article=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, param.getArticle());
        ps.executeUpdate();

        ps.close();

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

//    public void insertProductSizes(Product product) throws Exception {
//        Connection connection = DbConnectionFactory.getInstance().getConnection();
//        String query = "INSERT INTO product_size (product_article, size_id) VALUES (?,?)";
//        PreparedStatement ps = connection.prepareStatement(query);
//        ps.setLong(1, product.getArticle());
//
//        for (Size s : product.getSizes()) {
//            ps.setLong(2, s.getId());
//            ps.executeUpdate();
//        }
//
//        ps.close();
//    }
    private void removeProductSizes(Product product, Size size) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String query = "DELETE FROM product_size WHERE product_article=? AND size_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, product.getArticle());
        ps.setLong(2, size.getId());
        ps.executeUpdate();

        ps.close();
    }

    private void updateProductSizes(Product product) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        List<Size> dbSizes = getProductSizes(product);
        List<Size> productSizes = product.getSizes();

        for (Size s : dbSizes) {
            if (!productSizes.contains(s)) {
                removeProductSizes(product, s);
            }
        }

        for (Size s : productSizes) {
            if (!dbSizes.contains(s)) {
                String query = "INSERT INTO product_size (product_article, size_id) VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, product.getArticle());
                ps.setLong(2, s.getId());
                ps.executeUpdate();

                ps.close();
            }
        }

    }

    @Override
    public List<Product> getAll(Product param, List<String> columns, List<Object> values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getByPrimaryKey(Product param, Long primaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
