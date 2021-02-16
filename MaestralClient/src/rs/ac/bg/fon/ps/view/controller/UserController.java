/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.domain.UserImage;
import rs.ac.bg.fon.ps.exception.RequiredFieldsEmptyException;
import rs.ac.bg.fon.ps.view.constant.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.FrmPassword;
import rs.ac.bg.fon.ps.view.form.FrmUser;
import rs.ac.bg.fon.ps.view.util.FormMode;

/**
 *
 * @author Katarina
 */
public class UserController {

    private final FrmUser frmUser;
    private FrmPassword frmPassword;
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

        frmUser.btnRegisterAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }

            private void registerUser() {
                try {
                    validateForm();

                    User user = makeUserFromForm();
                    Controller.getInstance().saveUser(user);
                } catch (RequiredFieldsEmptyException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmUser, "Please fill out required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frmUser.getLblSee().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seePassword();
            }

            private void seePassword() {
                if (frmUser.getTxtPassword().echoCharIsSet()) {
                    frmUser.getTxtPassword().setEchoChar((char) 0);
                    frmUser.getLblSee().setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/eye-close.png")));
                } else {
                    frmUser.getTxtPassword().setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                    frmUser.getLblSee().setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/view/image/see.png")));
                }
            }
        });

        frmUser.btnEditAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });

        frmUser.btnChagePasswordAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPasswordForm();
            }

            private void openPasswordForm() {
                frmPassword = new FrmPassword((FrmMain) frmUser.getParent(), true);
                frmPassword.setLocationRelativeTo(frmUser);
                frmPassword.setVisible(true);
            }
        });

        frmUser.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (frmUser.getBtnSave().isVisible()) {
                    int answer = JOptionPane.showConfirmDialog(frmUser,
                            "Are you sure?\nChanges you made won't be saved?",
                            "Close window",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == 0) {
                        frmUser.dispose();
                    }
                } else {
                    frmUser.dispose();
                }
            }
        });

        frmUser.btnSaveAddACtionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }

            private void updateUser() {
                try {
                    validateForm();

                    User user = makeUserFromForm();
                    user.setId(((User) MainCordinator.getInstance().getParam(Constants.PARAM_USER)).getId());
                    String pass = ((User) MainCordinator.getInstance().getParam(Constants.PARAM_USER)).getPassword();
                    if (frmPassword != null && frmPassword.getPassword() != null) {
                        pass = frmPassword.getPassword();
                    }
                    user.setPassword(pass);
                    Controller.getInstance().updateUser(user);
                } catch (RequiredFieldsEmptyException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmUser, "Please fill out required fields.", "Error", JOptionPane.ERROR_MESSAGE);
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
        for (UserImage img : images) {
            frmUser.getCmbPictures().addItem(img);
        }
        User user = (User) MainCordinator.getInstance().getParam(Constants.PARAM_USER);
        if (mode == FormMode.FORM_ADD || user == null || user.getImage() == null) {
            frmUser.getCmbPictures().setSelectedIndex(0);
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
                frmUser.getLblProfileImage().setIcon(new ImageIcon("images/unknown.png"));
                frmUser.getBtnEdit().setVisible(false);
                frmUser.getBtnRegister().setVisible(true);
                frmUser.getBtnChangePassword().setVisible(false);
                frmUser.getBtnSave().setVisible(false);
                frmUser.getTxtPassword().setVisible(true);
                frmUser.getLblPassword().setVisible(true);
                frmUser.getLblSee().setVisible(true);
                break;
            case FORM_DETAIL:
                frmUser.getTxtPassword().setVisible(false);
                frmUser.getLblPassword().setVisible(false);
                frmUser.getLblSee().setVisible(false);
                frmUser.getBtnEdit().setVisible(true);
                frmUser.getBtnRegister().setVisible(false);
                frmUser.getBtnChangePassword().setVisible(false);
                frmUser.getBtnSave().setVisible(false);
                frmUser.getTxtFisrtName().setEditable(false);
                frmUser.getTxtLastName().setEditable(false);
                frmUser.getTxtUsername().setEditable(false);
                frmUser.getCbAdmin().setEnabled(false);
                frmUser.getCmbPictures().setEnabled(false);
                fillForm();
                break;
            case FORM_EDIT:
                frmUser.getBtnEdit().setVisible(false);
                frmUser.getBtnSave().setVisible(true);
                frmUser.getBtnChangePassword().setVisible(true);
                frmUser.getTxtFisrtName().setEditable(true);
                frmUser.getTxtLastName().setEditable(true);
                frmUser.getTxtUsername().setEditable(true);
                frmUser.getCbAdmin().setEnabled(true);
                frmUser.getCmbPictures().setEnabled(true);
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

    private void validateForm() throws RequiredFieldsEmptyException {
        resetErrors();
        boolean errors = false;

        if (frmUser.getTxtFisrtName().getText() == null || frmUser.getTxtFisrtName().getText().isEmpty()) {
            frmUser.getLblFNameError().setText("*Required");
            errors = true;
        }
        if (frmUser.getTxtLastName().getText() == null || frmUser.getTxtLastName().getText().isEmpty()) {
            frmUser.getLblLNameError().setText("*Required");
            errors = true;
        }
        if (frmUser.getTxtUsername().getText() == null || frmUser.getTxtUsername().getText().isEmpty()) {
            frmUser.getLblUsernameError().setText("*Required");
            errors = true;
        }
        if (frmUser.getTxtPassword().isVisible()
                && (String.valueOf(frmUser.getTxtPassword().getPassword()) == null
                || String.valueOf(frmUser.getTxtPassword().getPassword()).isEmpty())) {
            frmUser.getLblPasswordError().setText("*Required");
            errors = true;
        }

        if (errors) {
            throw new RequiredFieldsEmptyException("Required fields can not be empty.");
        }
    }

    private void resetErrors() {
        frmUser.getLblFNameError().setText("");
        frmUser.getLblLNameError().setText("");
        frmUser.getLblUsernameError().setText("");
        frmUser.getLblPasswordError().setText("");
    }

    private User makeUserFromForm() {
        User user = new User();
        user.setFirstName(frmUser.getTxtFisrtName().getText());
        user.setLastName(frmUser.getTxtLastName().getText());
        user.setUsername(frmUser.getTxtUsername().getText());
        if (frmUser.getTxtPassword().isVisible()) {
            user.setPassword(String.valueOf(frmUser.getTxtPassword().getPassword()));
        }
        user.setAdmin(frmUser.getCbAdmin().isSelected());
        user.setImage((UserImage) frmUser.getCmbPictures().getSelectedItem());

        return user;
    }

    public void saveUserSuccess(User user) {
        Controller.getInstance().refreshUsersView();
        JOptionPane.showMessageDialog(frmUser,
                "User successfully registered with id " + user.getId(),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        MainCordinator.getInstance().addParam(Constants.PARAM_USER, user);
        setupComponents(FormMode.FORM_DETAIL);
    }

    public void saveUserFailed(String message) {
        JOptionPane.showMessageDialog(frmUser, "Registration failed!\n" + message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void updateSuccess(User user) {
        Controller.getInstance().refreshUsersView();
        MainCordinator.getInstance().addParam(Constants.PARAM_USER, user);
        JOptionPane.showMessageDialog(frmUser, "User successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        setupComponents(FormMode.FORM_DETAIL);
    }

    public void updateFailed(String message) {
        JOptionPane.showMessageDialog(frmUser, "Error occured. Update user failed.\n" + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
