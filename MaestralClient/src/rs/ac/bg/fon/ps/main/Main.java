/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.main;

import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.controller.Controller;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmLoadingScreen;

/**
 *
 * @author Katarina
 */
public class Main {

    public static void main(String[] args) {
        FrmLoadingScreen frmLoadingScreen = new FrmLoadingScreen();
        frmLoadingScreen.setVisible(true);

        try {

            for (int i = 0; i <= 100; i++) {
                Thread.sleep(40);
                frmLoadingScreen.jProgressBar.setValue(i);
            }
            frmLoadingScreen.setVisible(false);
            Controller.getInstance().connect(9000);
            MainCordinator.getInstance().openLogInForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        JOptionPane.showMessageDialog(null, "An error occured. Cancel invoice failed.", "Error", JOptionPane.ERROR_MESSAGE);
    }

}
