/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.domain.UserImage;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmUser;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class UserController {
    
    private final FrmUser frmUser;
    private boolean listen = true;
    private FormMode mode;

    public UserController(FrmUser frmUser) {
        this.frmUser = frmUser;
        addActionListener();
    }

    private void addActionListener() {
        frmUser.getCmbPictures().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && listen == true) {
                    UserImage img = (UserImage) e.getItem();
                    frmUser.getLblProfileImage().setIcon(new ImageIcon(img.getPath()));
                }
            }
        });
    }
    
    public void openForm(FormMode formMode) {
        this.mode = formMode;
        frmUser.setLocationRelativeTo(frmUser.getParent());
        frmUser.setResizable(false);
        prepareView(formMode);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmUser.setVisible(true);
    }

    private void prepareView(FormMode formMode) {
        Controller.getInstance().getImages();
        setupComponents(formMode);
    }

    public void fillCmbImages(List<UserImage> images) {
        listen = false;
        frmUser.getCmbPictures().removeAllItems();
        for (UserImage img: images) {
            frmUser.getCmbPictures().addItem(img);
        }
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_USER);
        if (user == null || mode == FormMode.FORM_ADD) {
            frmUser.getCmbPictures().setSelectedIndex(-1);
        } else {
            frmUser.getCmbPictures().setSelectedItem(user.getImage());
        }
        listen = true;
    }

    public void viewInitialisationFailed() {
        JOptionPane.showMessageDialog(frmUser, "View initialisation failed!", "Error", JOptionPane.ERROR_MESSAGE);
        frmUser.dispose();
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmUser.getBtnEdit().setVisible(false);
                frmUser.getBtnRegister().setVisible(true);
                frmUser.getBtnChangePassword().setVisible(false);
                frmUser.getTxtPassword().setVisible(true);
                frmUser.getLblPassword().setVisible(true);
                frmUser.getLblProfileImage().setIcon(new ImageIcon("images/unknown.png"));
                break;
            case FORM_DETAIL:
                frmUser.getTxtPassword().setVisible(false);
                frmUser.getLblPassword().setVisible(false);
                frmUser.getBtnEdit().setVisible(true);
                frmUser.getBtnRegister().setVisible(false);
                frmUser.getBtnChangePassword().setVisible(true);
                fillForm();
                break;
        }
    }

    private void fillForm() {
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_USER);
        frmUser.getLblProfileImage().setIcon(new ImageIcon(user.getImage().getPath()));
        frmUser.getTxtFisrtName().setText(user.getFirstName());
        frmUser.getTxtLastName().setText(user.getLastName());
        frmUser.getTxtUsername().setText(user.getUsername());
        frmUser.getCbAdmin().setSelected(user.isAdmin());
    }
    
}
