/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.so;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class GetFilteredUsers extends AbstractSystemOperation{
    
    private final List<String> columns;
    private final List<Object> values;
    private List<User> users;

    public GetFilteredUsers(List<String> columns, List<Object> values) {
        this.columns = columns;
        this.values = values;
        this.users = new ArrayList<>();
    }

    @Override
    protected void checkPreconditions() throws Exception {
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        List<User> dbUsers = new ArrayList<>();
        if (columns.contains("admin")) {
            int index = columns.indexOf("admin");
            dbUsers = (List<User>) repository.getAll(new User(), Arrays.asList(columns.get(index)), Arrays.asList(values.get(index)));
        } else {
            dbUsers = (List<User>) repository.getAll(new User());
        }
        
        if (columns.contains("name")) {
            int index = columns.indexOf("name");
            String name = (String) values.get(index);
            for (User u: dbUsers) {
                if (u.getUsername().contains(name) || String.valueOf(u).contains(name)) {
                    users.add(u);
                }
            }
        } else {
            users = dbUsers;
        }
    }

    public List<User> getUsers() {
        return users;
    }
    
}
