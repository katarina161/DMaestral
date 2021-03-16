/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.component.table.UserTableModel;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmSearchUsers;

/**
 *
 * @author Katarina
 */
public class SearchUsersController {

    private final FrmSearchUsers frmSearchUsers;

    public SearchUsersController(FrmSearchUsers frmSearchUsers) {
        this.frmSearchUsers = frmSearchUsers;
        addActionListener();
    }

    private void addActionListener() {
        frmSearchUsers.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                frmSearchUsers.getTblUsers().clearSelection();
            }
        });

        frmSearchUsers.btnSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUsers(null);
            }
        });

        frmSearchUsers.getLblCancelFilter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cancelFilter();
            }

            private void cancelFilter() {
                frmSearchUsers.getTxtSearchText().setText("");
                frmSearchUsers.getCmbAdmin().setSelectedIndex(0);
                Controller.getInstance().refreshUsersView();
            }
        });

        frmSearchUsers.btnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seeDetails();
            }

            private void seeDetails() {
                int row = frmSearchUsers.getTblUsers().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(frmSearchUsers,
                            "Please select an employee.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    UserTableModel model = (UserTableModel) frmSearchUsers.getTblUsers().getModel();
                    User user = model.getUser(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_USER, user);
                    MainCordinator.getInstance().openUserDetailsForm();
                }
            }
        });
    }

    public void openForm() {
        frmSearchUsers.setLocationRelativeTo(frmSearchUsers.getParent());
        frmSearchUsers.setResizable(false);
        prepareView();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(SearchUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSearchUsers.setVisible(true);
    }

    private void prepareView() {
        gatherDataForTblUsres();
        fillCmbAdmin();
    }

    private void gatherDataForTblUsres() {
        Controller.getInstance().getAllUsers();
    }

    public void fillTblUsers(List<User> users) {
        UserTableModel model = new UserTableModel(users);
        frmSearchUsers.getTblUsers().setModel(model);
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmSearchUsers, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        frmSearchUsers.dispose();
    }

    private void fillCmbAdmin() {
        frmSearchUsers.getCmbAdmin().removeAllItems();
        frmSearchUsers.getCmbAdmin().addItem("All");
        frmSearchUsers.getCmbAdmin().addItem(Constants.ADMIN);
        frmSearchUsers.getCmbAdmin().addItem(Constants.NOT_ADMIN);
    }

    private void searchUsers(List<User> users) {
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        String text = frmSearchUsers.getTxtSearchText().getText().trim();
        String admin = (String) frmSearchUsers.getCmbAdmin().getSelectedItem();
        if (!text.isEmpty()) {
            columns.add("name");
            values.add(text);
        }
        switch (admin) {
            case Constants.ADMIN:
                columns.add("admin");
                values.add(true);
                break;
            case Constants.NOT_ADMIN:
                columns.add("admin");
                values.add(false);
                break;
        }
        if (!columns.isEmpty()) {
            Controller.getInstance().getFilteredUsers(columns, values);
        } else if (users == null) {
            Controller.getInstance().refreshUsersView();
        } else {
            UserTableModel model = (UserTableModel) frmSearchUsers.getTblUsers().getModel();
            model.setUsers(users);
        }

    }

    public FrmSearchUsers getFrmSearchUsers() {
        return frmSearchUsers;
    }

    public void refreshUsersView(List<User> users) {
        searchUsers(users);
    }

    public void setFilteredUsers(List<User> users) {
        UserTableModel model = (UserTableModel) frmSearchUsers.getTblUsers().getModel();
        model.setUsers(users);
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(frmSearchUsers, "No matching users found", "Search", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void filterUsersFailed(String message) {
        JOptionPane.showMessageDialog(frmSearchUsers, "Error occurred while filtering.\n" + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
