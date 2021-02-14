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
import rs.ac.bg.fon.ps.domain.Size;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbSize implements DbRepository<Size> {

    @Override
    public void add(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Size> getAll(Size param) throws Exception {
        List<Size> sizes = new ArrayList<>();
        
        String query = "SELECT id, number FROM size";
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {            
            Size size = new Size();
            size.setId(rs.getLong("id"));
            size.setSizeNumber(rs.getInt("number"));
            sizes.add(size);
        }
        rs.close();
        statement.close();
        
        return sizes;
    }

    @Override
    public void update(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Size param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Size> getAll(Size param, List<String> columns, List<Object> values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Size getByPrimaryKey(Size param, Long primaryKey) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
