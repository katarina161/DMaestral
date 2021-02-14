/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Katarina
 */
public class Product implements DomainObject, Serializable {

    private Long article;
    private String name;
    private Category category;
    private String description;
    private BigDecimal priceWithoutVAT;
    private BigDecimal priceWithVAT;
    private List<Size> sizes;

    public Product() {
    }

    public Product(Long article, String name, Category category, String description,
            BigDecimal priceWithoutVAT, BigDecimal priceWithVAT, List<Size> sizes) {
        this.article = article;
        this.name = name;
        this.category = category;
        this.description = description;
        this.priceWithoutVAT = priceWithoutVAT;
        this.priceWithVAT = priceWithVAT;
        this.sizes = sizes;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceWithoutVAT() {
        return priceWithoutVAT;
    }

    public void setPriceWithoutVAT(BigDecimal priceWithoutVAT) {
        this.priceWithoutVAT = priceWithoutVAT;
    }

    public BigDecimal getPriceWithVAT() {
        return priceWithVAT;
    }

    public void setPriceWithVAT(BigDecimal priceWithVAT) {
        this.priceWithVAT = priceWithVAT;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return article + " " + name;
    }

    @Override
    public String getTableName() {
        return "product";
    }

    @Override
    public String getParameterNames() {
        return "article, name, description, price, price_with_vat, category_id";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, '%s', '%s', %s, %s, %s", article, name, description, priceWithoutVAT, priceWithVAT, category.getId());
    }

    @Override
    public String getPrimaryKeyName() {
        return "article";
    }

    @Override
    public Long getPrimaryKeyValue() {
        return article;
    }

    @Override
    public void setPrimaryKey(Long key) {
        this.article = key;
    }

    @Override
    public String getJoinCondition() {
        return "JOIN category c ON p.category_id = c.id";
    }

    @Override
    public String getAllias() {
        return "p";
    }

    @Override
    public String getUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append("'")
                .append(", description='").append(description).append("'")
                .append(", price=").append(priceWithoutVAT)
                .append(", price_with_vat=").append(priceWithVAT)
                .append(", category_id=").append(category.getId());
        return sb.toString();
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
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
                
                list.add(product);
            }
        } catch (Exception e) {
            System.out.println("ERROR ResultSet " + getTableName());
        }

        return list;
    }

}
