/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import rs.ac.bg.fon.ps.domain.DomainObject;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Katarina
 */
public class RepositoryDbGeneric implements DbRepository<DomainObject> {

    @Override
    public void add(DomainObject object) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(object.getTableName())
                .append(" (").append(object.getParameterNames()).append(") ")
                .append("VALUES (").append(object.getParameterValues()).append(")");
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1);
            object.setPrimaryKey(id);
        }
        
        rs.close();
        statement.close();
    }

    @Override
    public List<DomainObject> getAll(DomainObject object) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *")
                .append(" FROM ").append(object.getTableName())
                .append(" ").append(object.getAllias());
        if (object.getJoinCondition() != null) {
            sb.append(" ").append(object.getJoinCondition());
        }
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<DomainObject> list = object.convertRSList(rs);

        rs.close();
        statement.close();

        return list;
    }

    @Override
    public List<DomainObject> getAll(DomainObject object, List<String> columns, List<Object> values) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *")
                .append(" FROM ").append(object.getTableName()).append(" ").append(object.getAllias());
        if (object.getJoinCondition() != null) {
            sb.append(" ").append(object.getJoinCondition());
        }
        int i = 0;
        sb.append(" WHERE ");
        while (i != columns.size()) { 
            sb.append(columns.get(i)).append("=");
            if (values.get(i) instanceof String) {
                sb.append("'").append((String) values.get(i)).append("'");
            } else if (values.get(i) instanceof Date) {
                sb.append("'").append(new java.sql.Date(((Date)values.get(i)).getTime())).append("'");
            } else {
                sb.append(values.get(i));
            }
            sb.append(" AND ");
            i++;
        }
        String query = sb.toString();
        query = query.substring(0, query.lastIndexOf("AND")).trim();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<DomainObject> list = object.convertRSList(rs);

        rs.close();
        statement.close();
        
        return list;
    }

    @Override
    public DomainObject getByPrimaryKey(DomainObject object, Long primaryKey) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(object.getTableName()).append(" ").append(object.getAllias());
        if (object.getJoinCondition() != null) {
            sb.append(" ").append(object.getJoinCondition());
        }
        sb.append(" WHERE ").append(object.getAllias()).append(".").append(object.getPrimaryKeyName()).append("=").append(primaryKey);
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<DomainObject> list = object.convertRSList(rs);
        
        rs.close();
        statement.close();
        
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public void update(DomainObject object) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(object.getTableName())
                .append(" SET ").append(object.getUpdateQuery())
                .append(" WHERE ").append(object.getPrimaryKeyName()).append("=").append(object.getPrimaryKeyValue());
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    public void delete(DomainObject object) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(object.getTableName())
                .append(" WHERE ").append(object.getPrimaryKeyName()).append("=").append(object.getPrimaryKeyValue());
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

}
