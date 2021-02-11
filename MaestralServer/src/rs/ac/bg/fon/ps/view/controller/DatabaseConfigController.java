/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.configuration.Configuration;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.view.form.FrmDatabaseConfig;

/**
 *
 * @author Katarina
 */
public class DatabaseConfigController {

    private final FrmDatabaseConfig frmDatabaseConfig;

    public DatabaseConfigController(FrmDatabaseConfig frmDatabaseConfig) {
        this.frmDatabaseConfig = frmDatabaseConfig;
        addActionListener();
    }

    private void addActionListener() {
        frmDatabaseConfig.btnChangeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeProperties();
            }

            private void changeProperties() {
                if (frmDatabaseConfig.getTxtUrl().getText() == null || frmDatabaseConfig.getTxtUrl().getText().isEmpty() 
                        || frmDatabaseConfig.getTxtUsername().getText() == null || frmDatabaseConfig.getTxtUsername().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frmDatabaseConfig, 
                            "Url and Username are required!", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int answer = JOptionPane.showConfirmDialog(frmDatabaseConfig, 
                        "Are you sure you want to make changes \nto database connection properties?", 
                        "Change", 
                        JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    try {
                        String url = frmDatabaseConfig.getTxtUrl().getText().trim();
                        String username = frmDatabaseConfig.getTxtUsername().getText().trim();
                        String password = frmDatabaseConfig.getTxtPassword().getText().trim();
                        Controller.getInstance().changeDatabaseConfig(url, username, password);
                        JOptionPane.showMessageDialog(frmDatabaseConfig, "Changes have been made successfully!");
                        frmDatabaseConfig.dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(DatabaseConfigController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(frmDatabaseConfig, "Error! Change failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public void openForm() {
        frmDatabaseConfig.setLocationRelativeTo(frmDatabaseConfig.getParent());
        frmDatabaseConfig.setResizable(false);
        prepareView();
        frmDatabaseConfig.setVisible(true);
    }

    private void prepareView() {
        frmDatabaseConfig.getTxtUrl().setText(Configuration.getInstance().getUrl());
        frmDatabaseConfig.getTxtUsername().setText(Configuration.getInstance().getUsername());
        frmDatabaseConfig.getTxtPassword().setText(Configuration.getInstance().getPassword());
    }

}
