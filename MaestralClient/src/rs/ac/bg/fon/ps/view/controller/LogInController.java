/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLogIn;
import rs.ac.bg.fon.ps.view.util.FormMode;

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
                    if (frmLogIn.getBgLanguage().getSelection() == null) {
                        JOptionPane.showMessageDialog(frmLogIn,
                                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.msg.LANGUAGE_MSG"),
                                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.msg.LANGUAGE_MSG_TITLE"),
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
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
                    frmLogIn.getLblUsernameError().setText(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.label.USERNAME_ERROR"));
                    frmLogIn.getTxtUsername().setText("");
                    frmLogIn.getTxtPassword().setText("");
                    errors = true;
                }
                if (password == null || password.isEmpty()) {
                    frmLogIn.getLblPasswordError().setText(java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.label.PASSWORD_ERROR"));
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

        frmLogIn.rbENGAddACtionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAppLanguage(Locale.ROOT);
                Controller.getInstance().setLanguage(Locale.ROOT);
            }
        });

        frmLogIn.rbSRBAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale l = new Locale("sr", "RS");
                setAppLanguage(l);
                Controller.getInstance().setLanguage(l);
            }
        });
    }

    private void prepareView() {
        frmLogIn.setLocationRelativeTo(null);
        frmLogIn.setResizable(false);
        frmLogIn.getContentPane().setBackground(Color.WHITE);
        frmLogIn.getRootPane().setDefaultButton(frmLogIn.getBtnLogIn());
    }

    private void setAppLanguage(Locale l) {
        Locale.setDefault(l);
        changeFrmLanguage();
    }

    private void changeFrmLanguage() {
        ResourceBundle bundle = ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle");
        frmLogIn.setTitle(bundle.getString("FrmLogIn.LOG_IN"));
        frmLogIn.getLblUsername().setText(bundle.getString("FrmLogIn.label.USERNAME"));
        frmLogIn.getLblPassword().setText(bundle.getString("FrmLogIn.label.PASSWORD"));
        frmLogIn.getLblSelectLanguage().setText(bundle.getString("FrmLogIn.label.SELECT_LANGUAGE"));
        frmLogIn.getBtnLogIn().setText(bundle.getString("FrmLogIn.LOG_IN"));
    }

    public void logInSuccess(User user) {
        MainCordinator.getInstance().addParam(Constants.PARAM_CURRENT_USER, user);

        JOptionPane.showMessageDialog(frmLogIn,
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.msg.WELCOME") + user.getFirstName(),
                java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.msg.SUCCESS"),
                JOptionPane.INFORMATION_MESSAGE);
        frmLogIn.dispose();

        FormMode mode = user.isAdmin() ? FormMode.FORM_ADMIN : FormMode.FORM_USER;
        MainCordinator.getInstance().openMainForm(mode);
    }

    public void logInFailed(String message) {
        JOptionPane.showMessageDialog(frmLogIn, message, java.util.ResourceBundle.getBundle("rs/ac/bg/fon/ps/resources/Bundle").getString("FrmLogIn.msg.LOGIN_ERROR"), JOptionPane.ERROR_MESSAGE);
    }

}
