/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Katarina
 */
public class Product implements Serializable{
    
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
        return article+ " " +name;
    }
    
    
    
}
