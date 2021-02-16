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
public class User implements DomainObject, Serializable {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean admin;
    private UserImage image;
    
    public User() {
    }
    
    public User(Long id, String firstName, String lastName, String username, String password, boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isAdmin() {
        return admin;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public UserImage getImage() {
        return image;
    }
    
    public void setImage(UserImage image) {
        this.image = image;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String getTableName() {
        return "user";
    }
    
    @Override
    public String getParameterNames() {
        String str = "username, password, first_name, last_name, admin";
        if (image != null) {
            str += ", image";
        }
        return str;
    }
    
    @Override
    public String getParameterValues() {
        String str = String.format("'%s', '%s', '%s', '%s', %s", username, password, firstName, lastName, admin);
        if (image != null) {
            str += String.format(", %s", image.getId());
        }
        return str;
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
        return "LEFT JOIN user_image ui ON u.image = ui.id";
    }
    
    @Override
    public String getAllias() {
        return "u";
    }
    
    @Override
    public String getUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("first_name='").append(firstName).append("'")
                .append(", last_name='").append(lastName).append("'")
                .append(", username='").append(username).append("'")
                .append(", password='").append(password).append("'")
                .append(", admin=").append(admin);
        if (image != null) {
            sb.append(", image=").append(image.getId());
        }
        return sb.toString();
    }
    
    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                UserImage img = new UserImage();
                img.setId(rs.getLong("ui.id"));
                img.setPath(rs.getString("ui.path"));
                
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setImage(img);
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("ERROR ResultSet " + getTableName());
        }
        
        return list;
    }
    
}
