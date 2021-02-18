/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.component.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Katarina
 */
public class UserTableModel extends AbstractTableModel{
    
    private List<User> users;
    private final String[] columnNames = new String[]{"Username", "Name", "Admin"};
    private final Class[] columnClasses = new Class[]{String.class, String.class, Boolean.class};

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnClasses.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = users.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return u.getUsername();
            case 1:
                return u.getFirstName()+ " " +u.getLastName();
            case 2:
                return u.isAdmin();
            default:
                return "N/A";
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int row) {
        return users.get(row);
    }
    
}
