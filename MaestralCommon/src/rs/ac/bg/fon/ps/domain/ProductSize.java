/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class ProductSize implements DomainObject {

    private Long id;
    private Product product;
    private Size size;

    public ProductSize() {
    }

    public ProductSize(Product product, Size size) {
        this.product = product;
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        return "product_size";
    }

    @Override
    public String getParameterNames() {
        return "id, product_article, size_id";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, %s, %s", id, product.getArticle(), size.getId());
    }

    @Override
    public String getPrimaryKeyName() {
        return "id";
    }

    @Override
    public Long getPrimaryKeyValue() {
        return id;
    }

    @Override
    public void setPrimaryKey(Long key) {
        this.id = key;
    }

    @Override
    public String getJoinCondition() {
        return "JOIN product p ON ps.product_article = p.article JOIN size s ON ps.size_id = s.id JOIN category c ON p.category_id = c.id";
    }

    @Override
    public String getAllias() {
        return "ps";
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {                
                Category category = new Category();
                category.setId(rs.getLong("c.id"));
                category.setName(rs.getString("c.name"));
                
                Product p = new Product();
                p.setArticle(rs.getLong("p.article"));
                p.setName(rs.getString("p.name"));
                p.setDescription(rs.getString("p.description"));
                p.setCategory(category);
                p.setPriceWithoutVAT(new BigDecimal(rs.getDouble("p.price")));
                p.setPriceWithVAT(new BigDecimal(rs.getDouble("p.price_with_vat")));
                
                Size s = new Size();
                s.setId(rs.getLong("s.id"));
                s.setSizeNumber(rs.getInt("s.number"));
                
                ProductSize ps = new ProductSize(p, s);
                ps.setId(rs.getLong("ps.id"));
                list.add(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR ResultSet " + getTableName());
        }
        return list;
    }
}
