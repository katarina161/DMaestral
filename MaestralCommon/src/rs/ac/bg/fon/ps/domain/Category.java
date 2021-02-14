/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Katarina
 */
public class Category implements DomainObject, Serializable{
    
    private Long id;
    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "category";
    }

    @Override
    public String getParameterNames() {
        return "id, name";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, '%s'", id, name);
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
        return null;
    }

    @Override
    public String getAllias() {
        return "c";
    }

    @Override
    public String getUpdateQuery() {
        return "name=" +name;
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {                
                Category c = new Category();
                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println("ERROR ResultSet " + getTableName());
        }
        return list;
    }
    
}
