/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class MainController {

    private final FrmMain frmMain;
    private FormMode mode;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    public void openForm(FormMode mode) {
        this.mode = mode;
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER);
        frmMain.getMenuUser().setText(user.getUsername());
        prepareView();
        frmMain.setVisible(true);
    }

    private void addActionListener() {
        frmMain.jmiProductNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jmiProductNewActionPerformed(evt);
            }

            private void jmiProductNewActionPerformed(ActionEvent evt) {
                MainCordinator.getInstance().openAddNewProductForm();
            }
        });

        frmMain.jmiProductSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmiProductSearchActionPerformed(e);
            }

            private void jmiProductSearchActionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllProductsForm(mode);
            }
        });

        frmMain.jmiLogOutAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logOut();
            }

            private void logOut() {
                Controller.getInstance().logOut();
            }
        });

        frmMain.jmiInvoiceNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openAddNewInvoiceForm();
            }
        });

        frmMain.jmiInvoiceSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllInvoicesForm();
            }
        });
        
        frmMain.jmiEmployerNewAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openAddNewUserForm();
            }
        });
        
        frmMain.jmiEmployerSearchAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openViewAllUsersForm();
            }
        });
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    private void prepareView() {
        frmMain.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmMain.setLocationRelativeTo(null);
        frmMain.getContentPane().setBackground(Color.WHITE);
        setupComponents();
    }

    private void setupComponents() {
        switch (mode) {
            case FORM_ADMIN:
                frmMain.getMiProductNew().setVisible(true);
                frmMain.getMiInvoiceNew().setVisible(true);
                frmMain.getMenuEmployer().setVisible(true);
                break;
            case FORM_USER:
                frmMain.getMiProductNew().setVisible(false);
                frmMain.getMiInvoiceNew().setVisible(false);
                frmMain.getMenuEmployer().setVisible(false);
                break;
        }
    }
    
    public void logOutSuccess() {
        MainCordinator.getInstance().removeParams();
        frmMain.dispose();
        MainCordinator.getInstance().openLogInForm();
    }

    public void logOutFailed() {
        JOptionPane.showMessageDialog(frmMain, "Error! You're still logged in!", "Error", JOptionPane.ERROR_MESSAGE);
    }

}
