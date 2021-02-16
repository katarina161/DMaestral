/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.Arrays;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class AddUser extends AbstractSystemOperation {

    private User user;

    public AddUser(User user) {
        this.user = user;
    }

    @Override
    protected void checkPreconditions() throws Exception {
        if (!repository.getAll(new User(), Arrays.asList("username"), Arrays.asList(user.getUsername())).isEmpty()) {
            throw new Exception("That username is already taken.");
        }
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        repository.add(user);
    }

}
