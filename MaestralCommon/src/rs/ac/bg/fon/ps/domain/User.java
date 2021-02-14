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
    private boolean administrator;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String username, String password, boolean administrator) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
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

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
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
        return "id, username, password, first_name, last_name, administrator";
    }

    @Override
    public String getParameterValues() {
        return String.format("%s, '%s', '%s', '%s', '%s', %s", id, username, password, firstName, lastName, administrator);
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
        return "u";
    }

    @Override
    public String getUpdateQuery() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(sb)
        return null;
    }

    @Override
    public List<DomainObject> convertRSList(ResultSet rs) {
        List<DomainObject> list = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("ERROR ResultSet " + getTableName());
        }

        return list;
    }

}
