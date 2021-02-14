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

/**
 *
 * @author Katarina
 */
public class Size implements DomainObject, Serializable{
    
    private Long id;
    private int sizeNumber;

    public Size() {
    }

    public Size(Long id, int sizeNumber) {
        this.id = id;
        this.sizeNumber = sizeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSizeNumber() {
        return sizeNumber;
    }

    public void setSizeNumber(int sizeNumber) {
        this.sizeNumber = sizeNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(sizeNumber);
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
        final Size other = (Size) obj;
        if (this.sizeNumber != other.sizeNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "size";
    }

    @Override
    public String getParameterNames() {
        return "id, number";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, %s", id, sizeNumber);
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
        return "s";
    }

    @Override
    public String getUpdateQuery() {
        return "number=" +sizeNumber;
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {                
                Size s = new Size();
                s.setId(rs.getLong("s.id"));
                s.setSizeNumber(rs.getInt("s.number"));
                
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("ERROR ResultSet " + getTableName());
        }
        
        return list;
    }
    
    
    
}
