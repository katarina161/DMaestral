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
    public List<T> getAll() throws Exception;
    public void update(T param) throws Exception;
    public void delete(T param) throws  Exception;
}
