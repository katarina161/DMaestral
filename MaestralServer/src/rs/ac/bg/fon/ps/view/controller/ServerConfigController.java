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
import rs.ac.bg.fon.ps.view.form.FrmServerConfig;

/**
 *
 * @author Katarina
 */
public class ServerConfigController {
    
    private final FrmServerConfig frmServerConfig;

    public ServerConfigController(FrmServerConfig frmServerConfig) {
        this.frmServerConfig = frmServerConfig;
        addActionListener();
    }

    private void addActionListener() {
        frmServerConfig.btnChangeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePort();
            }

            private void changePort() {
                if (frmServerConfig.getTxtPort().getText() == null || 
                        frmServerConfig.getTxtPort().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frmServerConfig, "Enter port number!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int port = Integer.parseInt(frmServerConfig.getTxtPort().getText());
                        if (port < 0 || port > 65535) {
                            JOptionPane.showMessageDialog(frmServerConfig, 
                                    "Port must be a number\nbetween 0 and 65535.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int answer = JOptionPane.showConfirmDialog(frmServerConfig,
                                "Are you sure you want to change port number?", 
                                "Change port", 
                                JOptionPane.YES_NO_OPTION);
                        if (answer == 0) {
                            Controller.getInstance().changePortNumber(port);
                            JOptionPane.showMessageDialog(frmServerConfig, "Port successfully changed!");
                            frmServerConfig.dispose();
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(frmServerConfig, "Enter port number!", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        Logger.getLogger(ServerConfigController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(frmServerConfig, "Error! Port change failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
    
    public void openForm() {
        frmServerConfig.setLocationRelativeTo(frmServerConfig.getParent());
        frmServerConfig.setResizable(false);
        prepareView();
        frmServerConfig.setVisible(true);
    }

    private void prepareView() {
        frmServerConfig.getTxtPort().setText(Configuration.getInstance().getPort()+"");
    }
    
}
