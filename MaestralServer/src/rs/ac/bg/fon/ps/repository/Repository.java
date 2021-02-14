/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 *
 * @author Katarina
 */
public interface Repository <T> {
    public void add(T param) throws Exception;
    public List<T> getAll(T param) throws Exception;
    public List<T> getAll(T param, List<String> columns, List<Object> values) throws Exception;
    public T getByPrimaryKey(T param, Long primaryKey) throws Exception;
    public void update(T param) throws Exception;
    public void delete(T param) throws  Exception;
}
