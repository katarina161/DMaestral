/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbUser implements DbRepository<User>{

    public RepositoryDbUser() {
    }
    
    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        
        String query = "SELECT id, first_name, last_name, username, password FROM user";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {            
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        rs.close();
        statement.close();
        
        return users;
    }

    @Override
    public void add(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
