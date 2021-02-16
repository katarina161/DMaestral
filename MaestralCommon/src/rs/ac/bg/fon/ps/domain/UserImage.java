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
import rs.ac.bg.fon.ps.domain.DomainObject;

/**
 *
 * @author Katarina
 */
public class UserImage implements DomainObject, Serializable{
    
    private Long id;
    private String path;

    public UserImage() {
    }

    public UserImage(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return path.substring(path.lastIndexOf("/")+1, path.lastIndexOf(".")).replace("_", " ");
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
        final UserImage other = (UserImage) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "user_image";
    }

    @Override
    public String getParameterNames() {
        return "path";
    }

    @Override
    public String getParameterValues() {
        return String.valueOf(id);
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
        return "ui";
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
                UserImage img = new UserImage();
                img.setId(rs.getLong("id"));
                img.setPath(rs.getString("path"));
                
                list.add(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR ResultSet " + getTableName());
        }
        return list;
    }
    
}
