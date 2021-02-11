/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;

/**
 *
 * @author Katarina
 */
public class LogInController {

    private final FrmLogIn frmLogIn;

    public LogInController(FrmLogIn frmLogIn) {
        this.frmLogIn = frmLogIn;
        addActionListener();
    }

    public void openForm() {
        frmLogIn.setVisible(true);
        prepareView();
    }

    private void addActionListener() {
        frmLogIn.logInAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInUser(evt);
            }

            private void logInUser(java.awt.event.ActionEvent evt) {
                try {
                    String username = frmLogIn.getTxtUsername().getText().trim();
                    String password = String.valueOf(frmLogIn.getTxtPassword().getPassword());

                    validateForm(username, password);

                    Controller.getInstance().logIn(username, password);
                } catch (RequiredFieldsEmptyException ex) {
                    ex.printStackTrace();
                }
            }

            private void validateForm(String username, String password) throws RequiredFieldsEmptyException {
                resetForm();
                boolean errors = false;

                if (username == null || username.isEmpty()) {
                    frmLogIn.getLblUsernameError().setText("Username can not be empty.");
                    frmLogIn.getTxtUsername().setText("");
                    frmLogIn.getTxtPassword().setText("");
                    errors = true;
                }
                if (password == null || password.isEmpty()) {
                    frmLogIn.getLblPasswordError().setText("Passord can not be empty.");
                    frmLogIn.getTxtPassword().setText("");
                    errors = true;
                }

                if (errors) {
                    throw new RequiredFieldsEmptyException("Username and/or password are empty.");
                }
            }

            private void resetForm() {
                frmLogIn.getLblUsernameError().setText("");
                frmLogIn.getLblPasswordError().setText("");
            }

        });
    }

    private void prepareView() {
        frmLogIn.setLocationRelativeTo(null);
        frmLogIn.setResizable(false);
        frmLogIn.getContentPane().setBackground(Color.WHITE);
        frmLogIn.getRootPane().setDefaultButton(frmLogIn.getBtnLogIn());
    }

    public void logInSuccess(User user) {
        MainCordinator.getInstance().addParam(Constants.PARAM_CURRENT_USER, user);
        
        JOptionPane.showMessageDialog(frmLogIn, "Welcome " + user.getFirstName(),
                "Success", JOptionPane.INFORMATION_MESSAGE);
        frmLogIn.dispose();

        MainCordinator.getInstance().openMainForm();
    }

    public void logInFailed(String message) {
        JOptionPane.showMessageDialog(frmLogIn, message, "Log in Error", JOptionPane.ERROR_MESSAGE);
    }

}
