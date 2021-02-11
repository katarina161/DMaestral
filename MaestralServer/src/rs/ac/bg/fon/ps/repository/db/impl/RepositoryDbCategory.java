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
import rs.ac.bg.fon.ps.domain.Category;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbCategory implements DbRepository<Category> {

    @Override
    public void add(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        
        String query = "SELECT id, name FROM category";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {            
            Category category = new Category();
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            categories.add(category);
        }
        rs.close();
        statement.close();
        
        return categories;
    }

    @Override
    public void update(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Category param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
