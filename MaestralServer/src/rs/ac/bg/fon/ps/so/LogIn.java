/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.List;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.IncorrectPasswordException;
import rs.ac.bg.fon.ps.exception.UnknownUserException;

/**
 *
 * @author Katarina
 */
public class LogIn extends AbstractSystemOperation {

    private String username;
    private String password;
    private User user;

    public LogIn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        List<User> users = repository.getAll(new User());
        if (users.isEmpty()) {
            throw new Exception(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("LogIn.NO_USERS"));
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    this.user = user;
                } else {
                    throw new IncorrectPasswordException(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("LogIn.INVALID_PASSWORD"));
                }
            }
        }
        if (user == null) {
            throw new UnknownUserException(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("LogIn.UNKNOWN_USER"));
        }
    }

    public User getUser() {
        return user;
    }

}
