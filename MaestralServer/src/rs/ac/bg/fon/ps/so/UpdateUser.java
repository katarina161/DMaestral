/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.Arrays;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class UpdateUser extends AbstractSystemOperation{
    
    private User user;

    public UpdateUser(User user) {
        this.user = user;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        List<User> dbUsers = (List<User>) repository.getAll(new User(), Arrays.asList("username"), Arrays.asList(user.getUsername()));
        if (!dbUsers.isEmpty() && !(dbUsers.get(0).getId()).equals(user.getId())) {
            throw new Exception("That username is already taken.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        repository.update(user);
    }
    
}
