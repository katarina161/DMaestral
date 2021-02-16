/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class GetAllUsers extends AbstractSystemOperation{
    
    private List<User> users;

    public GetAllUsers() {
        this.users = new ArrayList<>();
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        users = (List<User>) repository.getAll(new User());
    }

    public List<User> getUsers() {
        return users;
    }
    
}
